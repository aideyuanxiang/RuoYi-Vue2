import request from '@/utils/request'

// 查询旅游信息列表
export function listTravel(query) {
  return request({
    url: '/travel/travel/list',
    method: 'get',
    params: query
  })
}

// 查询旅游信息详细
export function getTravel(travelId) {
  return request({
    url: '/travel/travel/' + travelId,
    method: 'get'
  })
}

// 新增旅游信息
export function addTravel(data) {
  return request({
    url: '/travel/travel',
    method: 'post',
    data: data
  })
}

// 修改旅游信息
export function updateTravel(data) {
  return request({
    url: '/travel/travel',
    method: 'put',
    data: data
  })
}

// 删除旅游信息
export function delTravel(travelId) {
  return request({
    url: '/travel/travel/' + travelId,
    method: 'delete'
  })
}
