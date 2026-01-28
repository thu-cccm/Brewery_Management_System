import request from '@/utils/request'

// ???方明细??
export function listDetail(query) {
  return request({
    url: '/system/detail/list',
    method: 'get',
    params: query
  })
}

// ???方明细??
export function getDetail(detailId) {
  return request({
    url: '/system/detail/' + detailId,
    method: 'get'
  })
}

// ???方明细
export function addDetail(data) {
  return request({
    url: '/system/detail',
    method: 'post',
    data: data
  })
}

// ???方明细
export function updateDetail(data) {
  return request({
    url: '/system/detail',
    method: 'put',
    data: data
  })
}

// ???方明细
export function delDetail(detailId) {
  return request({
    url: '/system/detail/' + detailId,
    method: 'delete'
  })
}
