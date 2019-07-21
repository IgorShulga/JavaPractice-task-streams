package ua.skillsup.practice.streams;

import org.assertj.core.data.MapEntry;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class HometaskTest {

    @Test
    public void shouldCountSumOfNumbersInTheList() throws Exception {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4, 5);

        //Replace here
        long quantity = listOfNumbers
                .stream()
                .reduce(0, (x, y) -> x + y);

        assertThat(quantity).isEqualTo(15L);
    }

    @Test
    public void shouldFilterAllNumbersGreaterThan5AndDividedBy2() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        //Replace here
        List<Integer> numbersGreaterThan5 = numbers
                .stream()
                .filter(integer -> integer > 5)
                .filter(integer -> integer % 2 == 0)
                .collect(Collectors.toList());

        assertThat(numbersGreaterThan5).contains(6, 8);
    }

    @Test
    public void shouldMultiplyAndTransformIntoStringEachElement() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        //Replace here
        List<String> multipliedNumbersAsString = numbers
                .stream()
                .map(integer -> integer * 2)
                .map(Object::toString)
                .collect(Collectors.toList());

        assertThat(multipliedNumbersAsString).contains("2", "4", "6", "8", "10");
    }

    @Test
    public void shouldCheckIfThereIsANumberGreaterThan4() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        //Replace here
        boolean anyNumberGreaterThan4 = numbers
                .stream()
                .anyMatch(integer -> integer > 4);

        assertThat(anyNumberGreaterThan4).isTrue();
    }

    @Test
    public void shouldCheckIfEachNumberIsEven() throws Exception {
        List<Integer> numbers = Arrays.asList(2, 4, 6);

        //Replace here
        boolean eachNumberIsPair = numbers
                .stream()
                .allMatch(integer -> integer % 2 == 0);

        assertThat(eachNumberIsPair).isTrue();
    }

    @Test
    public void shouldSortTheList() throws Exception {
        List<String> listOfWords = Arrays.asList("B", "A", "D", "E", "C");

        //Replace here
        List<String> sortedList = listOfWords
                .stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList());

        assertThat(sortedList).contains("A", "B", "C", "D", "E");
    }

    @Test
    public void shouldReduceOnlyPositiveValuesToCalculateMultiplyThem() throws Exception {
        List<BigDecimal> numbers = Arrays.asList(
                BigDecimal.valueOf(15),
                BigDecimal.valueOf(-8),
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(-8.6),
                BigDecimal.valueOf(2));

        //Replace here
        Optional<BigDecimal> reduced = numbers
                .stream()
                .filter(n -> n.compareTo(BigDecimal.ZERO) > 0)
                .reduce(BigDecimal::multiply);

        assertThat(reduced.isPresent()).isTrue();
        assertThat(reduced.get()).isEqualByComparingTo("300");
    }

    @Test
    public void shouldGroupBlogPostsByTheirTypes() {
        List<BlogPost> posts = Arrays.asList(
                new BlogPost("News item 1", "Author 1", BlogPostType.NEWS, 15),
                new BlogPost("Tech review 1", "Author 2", BlogPostType.REVIEW, 5),
                new BlogPost("Programming guide", "Author 1", BlogPostType.GUIDE, 20),
                new BlogPost("News item 2", "Author 2", BlogPostType.NEWS, 35),
                new BlogPost("Tech review 2", "Author 1", BlogPostType.REVIEW, 15));

        //Replace here
        Map<BlogPostType, List<BlogPost>> postsPerType = posts
                .stream()
                .collect(Collectors.groupingBy(BlogPost::getType));

        assertThat(postsPerType.get(BlogPostType.NEWS).size()).isEqualTo(2);
        assertThat(postsPerType.get(BlogPostType.GUIDE).size()).isEqualTo(1);
        assertThat(postsPerType.get(BlogPostType.REVIEW).size()).isEqualTo(2);
    }

    @Test
    public void shouldBeCollectedToMapWithBlogPostTitleAsKey() {
        List<BlogPost> posts = Arrays.asList(
                new BlogPost("News item 1", "Author 1", BlogPostType.NEWS, 15),
                new BlogPost("Tech review 1", "Author 2", BlogPostType.REVIEW, 5),
                new BlogPost("Programming guide", "Author 1", BlogPostType.GUIDE, 20),
                new BlogPost("News item 2", "Author 2", BlogPostType.NEWS, 35),
                new BlogPost("Tech review 2", "Author 1", BlogPostType.REVIEW, 15));

        //Replace here
        Map<String, BlogPost> postPerTitle = posts
                .stream()
                .collect(Collectors.toMap(
                        BlogPost::getTitle, blogPost -> blogPost,   //key=Title, value=blogpost
                        (blogPost, blogPost2) -> blogPost2,         //if same key take new blogpost
                        HashMap::new                                //return HashMap
                ));

        assertThat(postPerTitle.get("News item 1").getTitle()).isEqualTo("News item 1");
        assertThat(postPerTitle.get("Tech review 1").getTitle()).isEqualTo("Tech review 1");
        assertThat(postPerTitle.get("Programming guide").getTitle()).isEqualTo("Programming guide");
        assertThat(postPerTitle.get("News item 2").getTitle()).isEqualTo("News item 2");
        assertThat(postPerTitle.get("Tech review 2").getTitle()).isEqualTo("Tech review 2");
    }
}