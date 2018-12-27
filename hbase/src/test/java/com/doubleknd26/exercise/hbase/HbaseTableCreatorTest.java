package com.doubleknd26.exercise.hbase;

import com.google.common.collect.Lists;
import org.apache.directory.api.util.Strings;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by doubleknd26 on 01/11/2018.
 */
public class HbaseTableCreatorTest {

    @Test
    public void aa () {
        List<byte[]> splits = Lists.newArrayList("90105", "gz-c", "lgaka앢", "lotionsa", "mj주니어", "nikebabysf", "peacock", "reservam", "stila", "tv드레스", "w4", "가슴모아주는", "겨울왕국1000", "고양이화장실발판", "과일통일회용", "금성농협", "나이키루나로", "남성반", "남성오일프리", "남성페인트기모", "남자가죽자켓", "남자아디다스바", "남자쿠박", "내선묵", "네파츄리닝바지", "노트8그", "누전차단기5", "대싱디바화이트", "도장조각기", "레고10812듀플로", "리틀래빗남아용", "무선조종자동차맥", "미니테크", "미샤이탈프리즘섀도우4", "미즈노이그니터스4as2", "방한벽지", "벨벳아디다스", "브라운화분받침1", "브릿", "블랙야크눈썰매", "블루문지갑", "비너스남자", "빅사이즈바람막이노랑1", "삼성clp360", "삼성공기청정기ax80n", "삼성스포츠기어", "새무", "세탁조클리너산", "숲속의하", "스파램", "슬래진저듁", "시크릿쥬쥬+수저", "십자led등", "쎄일록", "씨제이삼북", "아기니트레깅스.", "아디다스611", "아이오페에어쿠션인텐스커버2", "양키캔들그린", "오휘um", "원피스빅사이즈", "윤곽섲", "이니스프리달팽이", "이니스프리햊", "이월상품라코스테", "이케아무선충전", "인형스타킩", "일회용비닐팩", "자개보석함", "정수기보", "중국코드변환", "챔피온후드집업여성", "카매트모", "캔키즈", "코스트코불고기", "크록스장화성인", "타요옷", "패딩지", "프린트빅", "핸드폰케이스삼성노트l", "홈웨어바지", "후지필름카메라메모리", "휴대폰충전케이블갤럭시8", "흰색무지반팔티")
                .stream()
                .map(str -> str.getBytes(Charset.forName("UTF-8")))
                .collect(Collectors.toList());
    }
}