function delFruit(fid){
    if(confirm('是否确认删除？')){
        window.location.href='delete?fid='+fid;
    }
}

function page(pageNo){
    window.location.href="index?pageNo="+pageNo;
}
