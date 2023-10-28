package com.example.bootstudy.data.entity;

import com.example.bootstudy.data.entity.listener.CustomListener;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "listener")
@EntityListeners(CustomListener.class)
public class ListenerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
