import request from '@/utils/request'

// 查询åŽŸæ–™ä¿¡æ¯列表
export function listMaterial(query) {
  return request({
    url: '/system/material/list',
    method: 'get',
    params: query
  })
}

// 查询åŽŸæ–™ä¿¡æ¯详细
export function getMaterial(materialId) {
  return request({
    url: '/system/material/' + materialId,
    method: 'get'
  })
}

// 新增åŽŸæ–™ä¿¡æ¯
export function addMaterial(data) {
  return request({
    url: '/system/material',
    method: 'post',
    data: data
  })
}

// 修改åŽŸæ–™ä¿¡æ¯
export function updateMaterial(data) {
  return request({
    url: '/system/material',
    method: 'put',
    data: data
  })
}

// 删除åŽŸæ–™ä¿¡æ¯
export function delMaterial(materialId) {
  return request({
    url: '/system/material/' + materialId,
    method: 'delete'
  })
}
