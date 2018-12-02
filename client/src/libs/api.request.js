import HttpRequest from '@/libs/axios'
import config from '@/config'

const HOST = config.baseUrl[process.env.NODE_ENV]

const axios = new HttpRequest(HOST)
export default axios
