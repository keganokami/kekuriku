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
  }
}

export default function(_app: any, inject: any) {
  inject('validation', validation);
}