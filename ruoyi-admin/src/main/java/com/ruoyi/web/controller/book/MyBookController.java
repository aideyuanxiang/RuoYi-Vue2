package com.ruoyi.web.controller.book;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.MyBook;
import com.ruoyi.system.domain.MyBookType;
import com.ruoyi.system.service.IMyBookService;
import com.ruoyi.system.service.IMyBookTypeService;
import com.ruoyi.system.validate.Add;
import com.ruoyi.system.validate.Edit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class MyBookController extends BaseController {

    @Autowired
    private IMyBookService myBookService;

    @Autowired
    private IMyBookTypeService myBookTypeService;

    @Autowired
    private Validator validator;

    @GetMapping("/list")
    public TableDataInfo listMyBooks(MyBook myBook) {

        startPage();
        List<MyBook> myBooks = myBookService.findAll(myBook);
        
        return getDataTable(myBooks);
    }

    @PostMapping("/add")
    @PreAuthorize("@ss.hasPermi('library:book:add')")
    public AjaxResult addBook(@RequestBody MyBook myBook) {

        BeanValidators.validateWithException(validator,myBook);

        Long userId = SecurityUtils.getUserId();
        myBook.setBelongTo(userId);

        boolean save =myBookService.save(myBook);

        return save?AjaxResult.success("新增成功"):AjaxResult.error("新增失败");
    }

    @GetMapping("/info/{id}")
    public AjaxResult findInfoById(@PathVariable("id") Integer id){

        MyBook myBook = myBookService.getById(id);

        return AjaxResult.success(myBook);
    }

    @PutMapping("/edit")
    public AjaxResult editBook(@Validated @RequestBody MyBook myBook){

        boolean result = myBookService.updateById(myBook);

        return result?AjaxResult.success("修改成功"):AjaxResult.error("修改失败");
    }

    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteById(@PathVariable("id") Integer[] ids){

        boolean result=myBookService.removeByIds(Arrays.asList(ids));

        return result?AjaxResult.success("删除成功"):AjaxResult.error("删除失败");
    }

    @PostMapping("/import")
    public AjaxResult importExcelData(MultipartFile file) throws Exception {

        ExcelUtil<MyBook> util=new ExcelUtil<>(MyBook.class);
        List<MyBook> myBookList = util.importExcel(file.getInputStream());

        boolean result = myBookService.saveBatch(myBookList);

        return result?AjaxResult.success("导入成功"):AjaxResult.error("导入失败");
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response){

        ExcelUtil<MyBook> util=new ExcelUtil<>(MyBook.class);
        util.importTemplateExcel(response,"图书数据");
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response){

        List<MyBook> list = myBookService.list();
        ExcelUtil<MyBook> util =new ExcelUtil<>(MyBook.class);

        util.exportExcel(response,list,"图书信息");

    }

    @GetMapping("/subCount")
    public AjaxResult subCount(){

        Map<String,List<Object>> bookSubscribeCount =myBookService.getBookSubscribeCount();

        return AjaxResult.success(bookSubscribeCount);
    }

    @GetMapping("/type/list")
    public AjaxResult typeList(){

        List<MyBookType> list = myBookTypeService.list();

        return AjaxResult.success(list);
    }

}
