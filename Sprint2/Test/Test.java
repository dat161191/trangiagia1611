
import java.util.*;

public class Test {

    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("PHP");
        arrayList.add("Python");
        arrayList.add("Java");
        arrayList.add("");
//        arrayList.add(null);
        arrayList.add("C++");
        System.out.println("Các phần tử của ArrayList");
        System.out.print("\t" + arrayList + "\n");
        Iterator itr = arrayList.iterator();
        while (itr.hasNext()) {
            Object element = itr.next();
            System.out.println(element);
        }
        Collections.sort(arrayList);
        // show list
        for (String element : arrayList) {
            System.out.println(element);
        }

        List<String> linkedList = new LinkedList<String>();
        linkedList.add("PHP");
        linkedList.add("Python");
        linkedList.add("Java");
        linkedList.add("Python");
        linkedList.add("C++");
        System.out.println("Các phần tử của LinkedList");
        System.out.print("\t" + linkedList + "\n");

        // new LinkedHashSet() duy trì thứ tự chèn vào
        Set<String> set = new LinkedHashSet<>();
        set.add("PHP");
        set.add("C++");
        set.add("Python");
        set.add("Java");
        set.add("Java");
        set.add("C++");
        System.out.println("Các phần tử của Set");
        System.out.print("\t" + set + "\n");

        // new treeSet() sẽ sắp xếp các phần tử
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("PHP");
        treeSet.add("Python");
        treeSet.add("Java");
        treeSet.add("Java");
        treeSet.add("C++");
        System.out.println("Các phần tử của Set");
        System.out.print("\t" + treeSet + "\n");

        // new HashMap() có thể có 1 key null,và nhiều value null
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Windows", "2000");
        hashMap.put("Windows", "XP");
        hashMap.put("Windows", "");
        hashMap.put("", "");
        hashMap.put("", "");
        hashMap.put("Language2", "Java");
        hashMap.put("Language1", ".Net");
        System.out.println("Các phần tử của HashMap");
        System.out.print("\t" + hashMap + "\n");
//LinkedHashMap duy trì thứ t chèn vào
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Windows", "2000");
        map.put("Windows", "XP");
        map.put("Windows", "");
        map.put("", "");
        map.put(null, "");
        map.put("Language2", "Java");
        map.put("Language1", ".Net");
        System.out.println("Các phần tử của LinkedHashMap");
        System.out.print("\t" + map + "\n");

//TreeMap k chứa key null,sắp xếp tăng dân
        Map<String, String> map1 = new TreeMap<>();
        map1.put("Windows", "2000");
        map1.put("Windows", "XP");
        map1.put("Windows", "");
//        map1.put(null, "");
        map1.put("", "");
        map1.put("Language2", "Java");
        map1.put("Language1", ".Net");
        System.out.println("Các phần tử của TreeMap");
        System.out.print("\t" + map1);
    }

}
