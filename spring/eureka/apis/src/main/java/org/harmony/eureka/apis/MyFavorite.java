package org.harmony.eureka.apis;

import java.util.List;

/**
 * @author wuxii@foxmail.com
 */
public class MyFavorite {

    private Member member;
    private List<Movie> movies;

    public MyFavorite() {
    }

    public MyFavorite(Member member, List<Movie> movies) {
        this.member = member;
        this.movies = movies;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
