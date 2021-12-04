export const validation = {

  requiredRule(message = '入力してください') {
    return (value: any) => {
      return !!value || message;
    }
  },

  maxLengthRule(length: number, name: string) {
    return (value: string | undefined) => {
      const message = `${name}は${length}文字以内で入力してください`;
      if (!value) {
        return message;
      }
      return value.length <= length || message;
    }
  },

  runTimeRecodeRule() {
    return (value: number) => {
      if (typeof value === 'undefined') {
        return true;
      } else if (typeof value === 'string' && value === "") {
        return true;
      } else {
        const v = String(value);
        const re = /(^\d{2})\.(\d{2}$)/;
        return !!re.exec(v) || "記録は12.88の形式で入力してください。"
      }
    }
  }
}

export default function (_app: any, inject: any) {
  inject('validation', validation);
}