import EntryEvents from "../events/EntryEvents";

export default class CompeEntry {
  constructor(
    public name = "",
    public nameKana = "",
    public team = "",
    public phoneNum = "",
    public sex = "",
    public compeEvent: EntryEvents[] = []
  ) {}
}