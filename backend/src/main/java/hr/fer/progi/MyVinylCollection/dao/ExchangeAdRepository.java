package hr.fer.progi.MyVinylCollection.dao;

import hr.fer.progi.MyVinylCollection.domain.ExchangeAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExchangeAdRepository extends JpaRepository<ExchangeAd, Long> {

    @Query("SELECT e FROM exchange_ad e WHERE e.isActive = true")
    List<ExchangeAd> getActiveAds();

    @Modifying
    @Query("UPDATE exchange_ad e SET e.isActive = false, e.newOwner = :newOwner WHERE e.id = :adId")
    ExchangeAd exchangeOwners(@Param("adId") Long adId, @Param("newOwner") Long newOwner);

}
