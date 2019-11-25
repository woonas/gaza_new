package kr.gaza.myapp.flight.payment;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class PaymentController {
    @Autowired
    private SqlSession sqlSession;

    @GetMapping (value = "/JSP/flight/payment/payment")
    public ModelAndView bookingView1(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/JSP/flight/payment/payment");
        return modelAndView;
    }
}
