import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DeptSort {

    private List<Dept> resultList = new CopyOnWriteArrayList<Dept>();  //输出列表
    private List<Dept> deptList;  //输入列表

    /**
     * 排序
     * @param deptList
     */
    public DeptSort(List<Dept> deptList){
        this.deptList = deptList;
        for(Dept dept : this.deptList){
            if(dept.getParentid() == "" || dept.getParentid() == null){  //当父级为空
                resultList.add(dept);  //当父级为空时即顶级，首先放入输出列表
                findChildren(dept);  //查询下级
            }
        }
    }

    /**
     * 查询下级
     * @param dept
     */
    private void findChildren(Dept dept){
        List<Dept> childrenList = new CopyOnWriteArrayList<Dept>();
        //遍历输入列表，查询下级
        for(Dept d : deptList){
            if(dept.getId().equals(d.getParentid()))
                childrenList.add(d);
        }
        //遍历到最末端，无下级，退出遍历
        if(childrenList.isEmpty()){
            return;
        }
        //对下级进行遍历
        for(Dept childDept : childrenList){
            resultList.add(childDept);
            findChildren(childDept);
        }
    }

    public List<Dept> getResultList(){
        return resultList;
    }

    public static List<Dept> sort(List<Dept> originalList){
        return new DeptSort(originalList).getResultList();
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        List<Dept> originalList = new CopyOnWriteArrayList<Dept>();
        Dept dept = new Dept();

        dept = new Dept();
        dept.setId("122");
        dept.setName("三级b");
        dept.setParentid("12");
        originalList.add(dept);

        dept = new Dept();
        dept.setId("13");
        dept.setName("二级b");
        dept.setParentid("1");
        originalList.add(dept);

        dept = new Dept();
        dept.setId("121");
        dept.setName("三级a");
        dept.setParentid("12");
        originalList.add(dept);

        dept = new Dept();
        dept.setId("1");
        dept.setName("一级");
        originalList.add(dept);

        dept = new Dept();
        dept.setId("131");
        dept.setName("三级c");
        dept.setParentid("13");
        originalList.add(dept);

        dept = new Dept();
        dept.setId("12");
        dept.setName("二级a");
        dept.setParentid("1");
        originalList.add(dept);

        dept = new Dept();
        dept.setId("132");
        dept.setName("三级d");
        dept.setParentid("13");
        originalList.add(dept);

        List<Dept> resultList = sort(originalList);
        System.out.println("输入列表："+ originalList);
        System.out.println("输出列表："+ resultList);
    }
}
