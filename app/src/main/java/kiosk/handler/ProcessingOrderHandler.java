package kiosk.handler;

import java.math.BigDecimal;

import kiosk.category.SaleCategory;
import kiosk.manager.CartManager;
import kiosk.model.action.KioskAction;
import kiosk.model.action.MainMenuAction;
import kiosk.ui.UIFactory;

/**
 * 주문 처리 핸들러 클래스
 * 
 * 이 클래스는 장바구니의 총 금액에 할인율을 적용하여 최종 결제 금액을 계산하고,
 * 주문 완료 UI를 표시한 후 메인 메뉴로 이동하는 역할
 */
public class ProcessingOrderHandler implements ActionHandler {
    private final SaleCategory saleCategory;
    private final CartManager cartManager;
    private final UIFactory uiFactory;

    private ProcessingOrderHandler(SaleCategory saleCategory, CartManager cartManager, UIFactory uiFactory) {
        this.saleCategory = saleCategory;
        this.cartManager = cartManager;
        this.uiFactory = uiFactory;
    }

    /**
     * {@link ProcessingOrderHandler}의 인스턴스를 생성하는 팩토리 메서드.
     * @param parameter {@link ParameterDto} 인스턴스
     * @return {@link ProcessingOrderHandler} 인스턴스
     */
    public static ProcessingOrderHandler withParameter(ParameterDto parameter) {
        return new ProcessingOrderHandler(
                parameter.saleCategory(),
                parameter.cartManager(),
                parameter.uiFactory());
    }

    /**
     * 주문을 처리하는 메서드
     * 
     * @return KioskAction 객체
     */
    @Override
    public KioskAction handle() {
        BigDecimal totalPrice = cartManager.getTotalPrice();
        BigDecimal finalPrice = totalPrice.subtract(totalPrice.multiply(saleCategory.getDiscountRate()));
        cartManager.clearCart();
        uiFactory.completeOrderUi(finalPrice);
        return new MainMenuAction();
    }

    /**
     * {@link ProcessingOrderHandler}의 파라미터 DTO 레코드
     * 
     * @param saleCategory {@link SaleCategory} 할인 카테고리
     * @param cartManager  {@link CartManager} 장바구니 매니저
     * @param uiFactory    {@link UIFactory} UI 팩토리
     */
    public static record ParameterDto(
            SaleCategory saleCategory,
            CartManager cartManager,
            UIFactory uiFactory) {
    }
}
