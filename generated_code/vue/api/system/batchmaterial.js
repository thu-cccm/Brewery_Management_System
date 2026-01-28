import request from '@/utils/request'

// 查询æ‰¹æ¬¡åŽŸæ–™列表
export function listBatchmaterial(query) {
  return request({
    url: '/system/batchmaterial/list',
    method: 'get',
    params: query
  })
}

// 查询æ‰¹æ¬¡åŽŸæ–™详细
export function getBatchmaterial(recordId) {
  return request({
    url: '/system/batchmaterial/' + recordId,
    method: 'get'
  })
}

// 新增æ‰¹æ¬¡åŽŸæ–™
export function addBatchmaterial(data) {
  return request({
    url: '/system/batchmaterial',
    method: 'post',
    data: data
  })
}

// 修改æ‰¹æ¬¡åŽŸæ–™
export function updateBatchmaterial(data) {
  return request({
    url: '/system/batchmaterial',
    method: 'put',
    data: data
  })
}

// 删除æ‰¹æ¬¡åŽŸæ–™
export function delBatchmaterial(recordId) {
  return request({
    url: '/system/batchmaterial/' + recordId,
    method: 'delete'
  })
}
