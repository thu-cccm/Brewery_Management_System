import request from '@/utils/request'

// ??质检报告??
export function listReport(query) {
  return request({
    url: '/system/report/list',
    method: 'get',
    params: query
  })
}

// ??质检报告??
export function getReport(reportId) {
  return request({
    url: '/system/report/' + reportId,
    method: 'get'
  })
}

// ??质检报告
export function addReport(data) {
  return request({
    url: '/system/report',
    method: 'post',
    data: data
  })
}

// ??质检报告
export function updateReport(data) {
  return request({
    url: '/system/report',
    method: 'put',
    data: data
  })
}

// ??质检报告
export function delReport(reportId) {
  return request({
    url: '/system/report/' + reportId,
    method: 'delete'
  })
}
