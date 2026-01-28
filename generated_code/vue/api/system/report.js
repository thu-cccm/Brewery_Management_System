import request from '@/utils/request'

// 查询è´¨æ£€æŠ¥å‘Š列表
export function listReport(query) {
  return request({
    url: '/system/report/list',
    method: 'get',
    params: query
  })
}

// 查询è´¨æ£€æŠ¥å‘Š详细
export function getReport(reportId) {
  return request({
    url: '/system/report/' + reportId,
    method: 'get'
  })
}

// 新增è´¨æ£€æŠ¥å‘Š
export function addReport(data) {
  return request({
    url: '/system/report',
    method: 'post',
    data: data
  })
}

// 修改è´¨æ£€æŠ¥å‘Š
export function updateReport(data) {
  return request({
    url: '/system/report',
    method: 'put',
    data: data
  })
}

// 删除è´¨æ£€æŠ¥å‘Š
export function delReport(reportId) {
  return request({
    url: '/system/report/' + reportId,
    method: 'delete'
  })
}
