import { createI18n } from 'vue-i18n'
import zh from './zh'
import en from './en'

const message = {
  en,
  zh,
}
const language = (navigator.language || 'en').toLocaleLowerCase()
 // 这是获取浏览器的语言
const i18n = createI18n({
  locale: localStorage.getItem('lang') || language.split('-')[0] || 'en', // 设置语言类型
  legacy: false, // 如果要支持compositionAPI，此项必须设置为false;
  globalInjection: true, // 全局注册$t方法
  messages: message,
})

export default i18n