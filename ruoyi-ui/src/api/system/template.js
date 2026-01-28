import request from '@/utils/request'

// 查询é…æ–¹æ¨¡æ¿列表
export function listTemplate(query) {
  return request({
    url: '/system/template/list',
    method: 'get',
    params: query
  })
}

// 查询é…æ–¹æ¨¡æ¿详细
export function getTemplate(templateId) {
  return request({
    url: '/system/template/' + templateId,
    method: 'get'
  })
}

// 新增é…æ–¹æ¨¡æ¿
export function addTemplate(data) {
  return request({
    url: '/system/template',
    method: 'post',
    data: data
  })
}

// 修改é…æ–¹æ¨¡æ¿
export function updateTemplate(data) {
  return request({
    url: '/system/template',
    method: 'put',
    data: data
  })
}

// 删除é…æ–¹æ¨¡æ¿
export function delTemplate(templateId) {
  return request({
    url: '/system/template/' + templateId,
    method: 'delete'
  })
}
