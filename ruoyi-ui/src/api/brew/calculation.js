import request from '@/utils/request'

export function calculateMaterial(templateId, targetOutput) {
  return request({
    url: '/brew/plan/calculateMaterial',
    method: 'get',
    params: { templateId, targetOutput }
  })
}

export function recommendCycle(temperature, humidity, wineType) {
  return request({
    url: '/brew/process/recommendCycle',
    method: 'get',
    params: { temperature, humidity, wineType }
  })
}

export function startProduction(planId, materialList) {
  return request({
    url: '/system/plan/startProduction',
    method: 'post',
    data: { planId, materialList }
  })
}
