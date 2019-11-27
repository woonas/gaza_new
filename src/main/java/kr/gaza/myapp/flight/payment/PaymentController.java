package kr.gaza.myapp.flight.payment;

import kr.gaza.myapp.flight.booking.BookingInterface;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    private SqlSession sqlSession;

    @PostMapping ("/JSP/flight/payment/payment")
    public ModelAndView paymentView1(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/JSP/flight/payment/payment");

        List<String> airportFroms = Arrays.asList(request.getParameter("airportFrom").split("#@!"));
        List<String> airportTos = Arrays.asList(request.getParameter("airportTo").split("#@!"));
        List<String> flightNums = Arrays.asList(request.getParameter("flightNum").split("#@!"));
        BookingInterface bookingDAO = sqlSession.getMapper(BookingInterface.class);

        modelAndView.addObject("airportFroms", airportFroms);
        modelAndView.addObject("airportTos", airportTos);
        modelAndView.addObject("flightList", bookingDAO.getFlightList(flightNums));

        return modelAndView;
    }

    @PostMapping("/JSP/flight/payment/payment-complete")
    public ModelAndView paymentView2(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/JSP/flight/payment/payment-complete");




        return modelAndView;
    }



























}
