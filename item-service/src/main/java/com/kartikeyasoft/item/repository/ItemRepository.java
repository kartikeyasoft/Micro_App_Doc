package com.kartikeyasoft.item.repository;
import com.kartikeyasoft.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ItemRepository extends JpaRepository<Item,Long>{}
