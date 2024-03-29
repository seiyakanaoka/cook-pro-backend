package com.example.project.presentation.controller.dish

import com.example.project.application.dto.dish.DishProcessesDTO
import com.example.project.application.usecase.dish.DishUseCaseImpl
import com.example.project.domain.enums.category.CategoryEnum
import com.example.project.presentation.form.dish.DishForm
import com.example.project.presentation.form.dish.PutDishForm
import com.example.project.presentation.mapper.dish.DishResponseMapper
import com.example.project.presentation.response.dish.*
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
class DishControllerImpl(
  private val dishUseCaseImpl: DishUseCaseImpl,
  private val dishResponseMapper: DishResponseMapper
) : DishController {
  /**
   * 料理一覧取得API
   */
  @GetMapping("/dish")
  override fun getDishes(
    @RequestAttribute("userId") userId: String,
    @RequestParam(name = "categories", required = false) categories: List<CategoryEnum>?
  ): DishesResponse? {
    val dishes = dishUseCaseImpl.getDishes(userId, categories)
    return dishResponseMapper.toDishesResponse(dishes)
  }

  /**
   * 料理登録API
   */
  @PostMapping("/dish")
  override fun postDish(@RequestAttribute("userId") userId: String, @RequestBody dishForm: DishForm): PostDishResponse {
    val dishFormDTO = dishResponseMapper.toDishFormDTO(dishForm);
    val dishId = dishUseCaseImpl.postDish(userId, dishFormDTO);
    return PostDishResponse(dishId)
  }

  /**
   * 料理詳細取得API
   */
  @GetMapping("/dish/{dishId}")
  override fun getDish(@RequestAttribute("userId") userId: String, @PathVariable dishId: String): DishDetailResponse =
    dishResponseMapper.toDetailResponse(dishUseCaseImpl.getDish(userId, dishId))

  /**
   * 料理編集API
   */
  @PutMapping("/dish/{dishId}")
  override fun putDish(
    @RequestAttribute("userId") userId: String,
    @PathVariable dishId: String,
    @RequestBody putDishForm: PutDishForm
  ): PutDishResponse {
    val putDishFormDTO = dishResponseMapper.toPutDishFormDTO(putDishForm)
    val id = dishUseCaseImpl.putDish(userId, dishId, putDishFormDTO)
    return PutDishResponse(id)
  }

  /**
   * 料理削除API
   */
  @DeleteMapping("/dish/{dishId}")
  override fun deleteDish(@PathVariable dishId: String): Unit {
    dishUseCaseImpl.deleteDish(dishId)
  }

  /**
   * 料理検索一覧取得API
   */
  @GetMapping("/dish/search")
  override fun getSearchDishes(
    @RequestAttribute("userId") userId: String,
    @RequestParam("dishName", required = false) dishName: String
  ): DishesSearchResponse {
    val searchDishes = dishUseCaseImpl.getSearchDishes(userId, dishName)
    return dishResponseMapper.toSearchResponse(searchDishes)
  }

  /**
   * 料理材料一覧取得API
   */
  @GetMapping("/dish/{dishId}/material")
  override fun getMaterials(@PathVariable dishId: String): DishMaterialsResponse {
    val dishMaterials = dishUseCaseImpl.getMaterials(dishId)
    return dishResponseMapper.toDishMaterials(dishMaterials)
  }

  /**
   * 料理工程取得API
   */
  @GetMapping("/dish/{dishId}/process")
  override fun getProcesses(
    @RequestAttribute("userId") userId: String,
    @PathVariable dishId: String
  ): DishProcessesDTO =
    dishUseCaseImpl.getProcesses(userId, dishId)
}