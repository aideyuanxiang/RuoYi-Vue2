import request from '@/utils/request'

// 查询选课信息列表
export function listChoose(query) {
  return request({
    url: '/course/choose/list',
    method: 'get',
    params: query
  })
}

// 查询选课信息详细
export function getChoose(chooseId) {
  return request({
    url: '/course/choose/' + chooseId,
    method: 'get'
  })
}

// 新增选课信息
export function addChoose(data) {
  return request({
    url: '/course/choose',
    method: 'post',
    data: data
  })
}

// 修改选课信息
export function updateChoose(data) {
  return request({
    url: '/course/choose',
    method: 'put',
    data: data
  })
}

// 删除选课信息
export function delChoose(chooseId) {
  return request({
    url: '/course/choose/' + chooseId,
    method: 'delete'
  })
}
