import request from '@/utils/request'

// 查询全国通用地区码列表
export function listZone(query) {
  return request({
    url: '/travel/zone/list',
    method: 'get',
    params: query
  })
}

// 查询全国通用地区码详细
export function getZone(zoneId) {
  return request({
    url: '/travel/zone/' + zoneId,
    method: 'get'
  })
}

// 新增全国通用地区码
export function addZone(data) {
  return request({
    url: '/travel/zone',
    method: 'post',
    data: data
  })
}

// 修改全国通用地区码
export function updateZone(data) {
  return request({
    url: '/travel/zone',
    method: 'put',
    data: data
  })
}

// 删除全国通用地区码
export function delZone(zoneId) {
  return request({
    url: '/travel/zone/' + zoneId,
    method: 'delete'
  })
}
