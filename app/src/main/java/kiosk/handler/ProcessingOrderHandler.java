package kiosk.handler;

import java.math.BigDecimal;

import kiosk.category.SaleCategory;
import kiosk.manager.CartManager;
import kiosk.model.KioskAction;
import kiosk.ui.KioskUI;

public class ProcessingOrderHandler implements ActionHandler {
    private final SaleCategory saleCategory;
    private final CartManager cartManager;
    private final KioskUI kioskUI;

    private ProcessingOrderHandler(SaleCategory saleCategory, CartManager cartManager, KioskUI kioskUI) {
        this.saleCategory = saleCategory;
        this.cartManager = cartManager;
        this.kioskUI = kioskUI;
    }

    /**
     * ProcessingOrderHandler의 인스턴스를 생성하는 팩토리 메서드.
     * @param parameter
     * @return ProcessingOrderHandler 인스턴스
     */
    public static ProcessingOrderHandler withParameter(ParameterDto parameter) {
        return new ProcessingOrderHandler(
                parameter.saleCategory(),
                parameter.cartManager(),
                parameter.kioskUI());
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
        kioskUI.completeOrderUi(finalPrice);
        return new KioskAction.MainMenu();
    }

    /**
     * ProcessingOrderHandler의 파라미터 DTO 레코드
     * 
     * @param saleCategory 할인 카테고리
     * @param cartManager  장바구니 매니저
     * @param kioskUI      키오스크 UI
     */
    public static record ParameterDto(
            SaleCategory saleCategory,
            CartManager cartManager,
            KioskUI kioskUI) {
    }
}
