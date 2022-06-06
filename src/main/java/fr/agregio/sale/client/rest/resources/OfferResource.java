package fr.agregio.sale.client.rest.resources;

import fr.agregio.sale.infra.postgres.adapters.OfferSearchAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/offers")
public class OfferResource {

  private final OfferSearchAdapter searchAdapter; // /!\ Don't do that in a real case

  @GetMapping
  @Operation(summary = "Search for offers using RSQL syntax")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Represents a page of offers (maybe empty)",
        content = @Content(schema = @Schema(implementation = Page.class))),
  })
  public ResponseEntity<?> search(
      @RequestParam("page") int page,
      @RequestParam("size") int size,
      @RequestParam(value = "query", required = false) String query,
      @RequestParam(value = "sort", required = false) String sort) {
    return searchAdapter
        .search(page, size, query, sort)
        .fold(error -> ResponseEntity.badRequest().body(error), ResponseEntity::ok);
  }
}
