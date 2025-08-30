package com.veekesh.project.uber.uberApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    private Double balance = 0.0;

    @OneToMany(mappedBy = "wallet" , fetch = FetchType.LAZY)
    private Set<WalletTransaction> transactions;

}
