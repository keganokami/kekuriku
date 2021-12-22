package com.rakuriku.rakuriku.presentation.controller.compe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rakuriku.rakuriku.domain.model.compe.CompeEntity;
import com.rakuriku.rakuriku.domain.model.compe.CompeEntryManagementEntity;
import com.rakuriku.rakuriku.domain.model.compe.EntriesEntity;
import com.rakuriku.rakuriku.domain.service.compe.CompeService;
import com.rakuriku.rakuriku.infra.JWTProvider;
import com.rakuriku.rakuriku.presentation.controller.compe.request.CompeRequest;
import com.rakuriku.rakuriku.presentation.controller.compe.request.EntryCompeRequest;
import com.rakuriku.rakuriku.presentation.controller.compe.response.CompeResponse;
import com.rakuriku.rakuriku.presentation.controller.compe.response.CreateCompeResultResponse;
import com.rakuriku.rakuriku.presentation.controller.compe.response.EntryCompeResultResonse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/compe")
@RequiredArgsConstructor
public class CompeController {

	private final CompeService compeService;
	private final JWTProvider provider;
	private final CompeFactory compeFactory;

	@GetMapping("/my-compes")
	public List<CompeResponse> getCompes(HttpServletRequest httpServletRequest) {
		// headerはコンフィグにするか
		String adminId = provider.getSubject(httpServletRequest.getHeader("x-auth-token"));
		List<CompeEntity> compeEntity = compeService.getCompes(adminId);
		return compeFactory.createCompeResponse(compeEntity);
	}

	@GetMapping("/my-compe/{compeId}")
	public CompeResponse getCompe(@PathVariable String compeId, HttpServletRequest httpServletRequest) {
		// adminIdで取得したリストに対して、検索をかけるとセキュリティは上がるかコストがかかる（検討）
		CompeEntity compeEntity = compeService.getCompe(compeId);
		return compeFactory.createCompeResponse(compeEntity);
	}

	@PostMapping("/new")
	public CreateCompeResultResponse createCompe(@RequestBody CompeRequest request, HttpServletRequest httpServletRequest) {
		String adminId = provider.getSubject(httpServletRequest.getHeader("x-auth-token"));
		CompeEntity compeEntity;
		try {
			compeEntity = compeFactory.createCompeEntity(adminId, request);
			CompeEntity result = compeService.createCompe(compeEntity);
			return new CreateCompeResultResponse(result.getCompeId(), "大会の作成が完了しました。", "");
		} catch (JsonProcessingException e) {
			return new CreateCompeResultResponse("", "", "大会の作成に失敗しました。もう一度やり直してください。");
		}
	}

	@PostMapping("/entry/new")
	public EntryCompeResultResonse entryCompe(@RequestBody EntryCompeRequest request) {
		String compeId = request.getCompeId();
		EntriesEntity entry = compeFactory.createEntriesEntity(request);

		CompeEntryManagementEntity entried = compeService.entryCompe(compeId, entry, request.getCompeEvent());
		return new EntryCompeResultResonse("entried.getId()", "大会への申込が完了しました。", "");
	}
}
