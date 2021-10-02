package library.application.service.reservation;

import library.LibraryDBTest;
import library.application.service.material.MaterialQueryService;
import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.entry.Keyword;
import library.domain.model.material.instock.EntryInStockList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@LibraryDBTest
class MaterialQueryServiceTest {
    @Autowired
    MaterialQueryService materialQueryService;

    @Test
    void 本を検索できる() {
        Keyword keyword = new Keyword("ハンドブック");
        EntryInStockList materials = materialQueryService.search(keyword);

        assertAll(
                () -> assertEquals(1, materials.size()),
                () -> assertEquals(
                        "RDRA2.0 ハンドブック: 軽く柔軟で精度の高い要件定義のモデリング手法 (神崎善司)",
                        materials.asList().get(0).describeMaterial()));
    }

    @Test
    void 本を取得できる() {
        Entry entry = materialQueryService.findMaterial(new EntryNumber(1));
        assertEquals(entry.entryNumber().value(), 1);
    }
}