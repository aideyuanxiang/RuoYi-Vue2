import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

export function getBookList(query) {
  return request({
    url: '/books/list',
    method: 'get',
    params:query
  })
}

export function addBook(book) {

  /**
   * 序列化：
   * 将js对象转换为便于网络传输json字符串，json字符串又会被转换为 字节流(1010101010101) -> 网络
   * data属性表示会自动将js对象转换成json字符串放到请求体中 序列化
   * 后端通过@RequestBody注解来解析json字符串成为一个java 对象 反序列化
   */
  return request({
    url: '/books/add',
    method: 'post',
    data: book
  })
}

export function getBook(bookId){

  return request({
    url:'/books/info/' + parseStrEmpty(bookId),
    method:'get'
  })
}

export function updateBook(data){

  return request({
    url:'/books/edit',
    method:'put',
    data:data
  })
}

export function deleteById(id){

  return request({
    url:'/books/delete/'+parseStrEmpty(id),
    method:'delete'
  })
}

export function subCount(){

  return request({
    url:'/books/subCount',
    method:'get'
  })
}

export function listbookTypes(){

  return request({
    url:'/books/type/list',
    method:'get'
  })
}
