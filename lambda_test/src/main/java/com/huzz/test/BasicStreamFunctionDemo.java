package com.huzz.test;

import com.huzz.model.Album;
import com.huzz.model.Artist;
import com.huzz.model.Track;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 常用流操作demo
 */
public class BasicStreamFunctionDemo {

    public List<Album> albumList = new ArrayList<>();

    private List<Track> trackList = new ArrayList<>();

    private List<Artist> artist = new ArrayList<>();

    //初始化数据
    @Before
    public void init() {
        List<Artist> members = new ArrayList<>();
        members.add(new Artist("科兰兄","上海","中国"));
        members.add(new Artist("科兰兄1","上海","中国"));
        members.add(new Artist("科兰兄2","上海","中国"));
        members.add(new Artist("科兰兄3","上海","中国"));
        artist.add(new Artist("屎壳郎",members,"北京","中国"));
        artist.add(new Artist("花儿乐队",members.subList(1,3),"华盛顿","美国"));
        artist.add(new Artist("五月天","伦敦","英国"));
        artist.add(new Artist("水木年华","曼谷","泰国"));
        artist.add(new Artist("苏打绿","悉尼","澳大利亚"));

        trackList.add(new Track("爱你一万年",300));
        trackList.add(new Track("十年",10));
        trackList.add(new Track("千万次我找你",251));
        trackList.add(new Track("勿忘心安",341));
        trackList.add(new Track("天下",134));
        trackList.add(new Track("逆战",309));
        trackList.add(new Track("情人",321));
        trackList.add(new Track("宿敌",134));

        albumList.add(new Album("天下",trackList, artist));

        albumList.add(new Album("春光灿烂猪八戒",trackList.subList(0,1), artist.subList(0,1)));

        albumList.add(new Album("无敌是多么寂寞",trackList.subList(0,5), artist.subList(1,3)));
    }

    @Test
    public void test() {
        Artist list =new Artist();
        list.setName("屎壳郎");
        list.setOrigin("中国");
        List<Artist> artists = new ArrayList<Artist>();
        artists.add(list);
        Artist list1 =new Artist();
        list1.setName("屎壳郎1");
        list1.setOrigin("中国1");
        artists.add(list1);
        //过滤
        List<Artist> collect = artists.stream().filter(art -> art.getName().contains("1")).collect(Collectors.toList());
        System.out.println(collect);
        //
        List<String> collect1 = artists.stream().map(Artist::getName).collect(Collectors.toList());
        System.out.println(collect1);
    }

    //查出大于1分钟的歌曲名称
    @Test
    public void test1() {
        ArrayList<Album> albums = new ArrayList<>();
        Set<String> collect = albums.stream()
                .flatMap(album -> album.getTracks().stream())
                .filter(track -> track.getLength() > 60)
                .map(Track::getName).collect(Collectors.toSet());
    }

    //求和
    @Test
    public void test2() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3,4,5,6,7,8,9,10);
        int i = addUp(integerStream);
        System.out.println(i);
        int count = Stream.of("1", "2", "3","4","5","6","7","8","9","10").mapToInt(str-> Integer.valueOf(str)).sum();
        System.out.println(count);
    }

    private int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0,(x,y) -> x+y).intValue();
    }

    //过滤专辑里大于三首歌曲的专辑
    @Test
    public void test3() {
        List<Album> collect = albumList.stream().filter(album -> album.getTracks().size() > 3).collect(Collectors.toList());
        System.out.println(collect.size());
    }

    //for循环为外部迭代，需要改为内部迭代
    @Test
    public void test4() {
        Integer reduce = artist.stream().map(artList1 -> {
            System.out.println(artList1.getMembers());
            return artList1.getMembers() == null ? 0 : artList1.getMembers().size();}).reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
    }

    //计算字符串中小写字母个数
    @Test
    public void test5() {
        String str = "fdsFDJFfdfjFHDFKfdfsdeFDSFDS";
        long count = str.chars().filter(Character::isLowerCase).count();
        System.out.println(count);
    }

    //找到字符串列表中小写字母最多的字符串，对于空列表返回Optional<String>
    @Test
    public void test6() {
        List<String> strings = new ArrayList<>();
        strings.add("abcdEFG");
        strings.add("abcdeFG");
        strings.add("EEEEEaaaaaaaaaaaa");
        strings.add("abcdefG");
        strings.add("abcdEfg");
        Optional<List<String>> strings1 = Optional.ofNullable(strings);
        if(strings1.isPresent()) {
            Optional<String> max = strings.stream().max(Comparator.comparing(s -> s.chars().filter(Character::isLowerCase).count()));
            System.out.println(max.get());
        } else {
            System.out.println(strings1);
        }
    }

    //只用reduce写出map操作的代码
    @Test
    public void test7() {
    }

    //只用reduce写出filter操作的代码
    @Test
    public void test8() {
    }
}