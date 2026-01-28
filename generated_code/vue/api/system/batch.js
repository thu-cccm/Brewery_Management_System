import request from '@/utils/request'

// 查询ç”Ÿäº§æ‰¹æ¬¡列表
export function listBatch(query) {
  return request({
    url: '/system/batch/list',
    method: 'get',
    params: query
  })
}

// 查询ç”Ÿäº§æ‰¹æ¬¡详细
export function getBatch(batchId) {
  return request({
    url: '/system/batch/' + batchId,
    method: 'get'
  })
}

// 新增ç”Ÿäº§æ‰¹æ¬¡
export function addBatch(data) {
  return request({
    url: '/system/batch',
    method: 'post',
    data: data
  })
}

// 修改ç”Ÿäº§æ‰¹æ¬¡
export function updateBatch(data) {
  return request({
    url: '/system/batch',
    method: 'put',
    data: data
  })
}

// 删除ç”Ÿäº§æ‰¹æ¬¡
export function delBatch(batchId) {
  return request({
    url: '/system/batch/' + batchId,
    method: 'delete'
  })
}
