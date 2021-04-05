package br.com.zup.gateway.data.domain;

import javax.persistence.*;

@Entity
@Table(name = "urlsNotification")
public class UrlNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "url", nullable = false)
    private String url;
    @Column(name = "method", nullable = false)
    private MethodType method;

    @Deprecated
    public UrlNotification(){}

    public UrlNotification(Long id, String url, MethodType method) {
        this.id = id;
        this.url = url;
        this.method = method;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public MethodType getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return "UrlNotification{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", method=" + method +
                '}';
    }
}
