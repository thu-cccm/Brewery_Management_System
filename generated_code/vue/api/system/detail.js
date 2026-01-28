import request from '@/utils/request'

// 查询é…æ–¹æ˜Žç»†列表
export function listDetail(query) {
  return request({
    url: '/system/detail/list',
    method: 'get',
    params: query
  })
}

// 查询é…æ–¹æ˜Žç»†详细
export function getDetail(detailId) {
  return request({
    url: '/system/detail/' + detailId,
    method: 'get'
  })
}

// 新增é…æ–¹æ˜Žç»†
export function addDetail(data) {
  return request({
    url: '/system/detail',
    method: 'post',
    data: data
  })
}

// 修改é…æ–¹æ˜Žç»†
export function updateDetail(data) {
  return request({
    url: '/system/detail',
    method: 'put',
    data: data
  })
}

// 删除é…æ–¹æ˜Žç»†
export function delDetail(detailId) {
  return request({
    url: '/system/detail/' + detailId,
    method: 'delete'
  })
}
