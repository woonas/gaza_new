package kr.gaza.myapp.flight.booking;

import kr.gaza.myapp.aviation.product.ProductVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class BookingController {
    @Autowired
    private SqlSession sqlSession;

    @GetMapping (value = "/JSP/flight/booking/booking1")
    public ModelAndView bookingView1(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String city = request.getParameter("city");
        String iata = request.getParameter("iata");
        String trip_type = request.getParameter("trip_type");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/JSP/flight/booking/booking1");

        if (city != null) modelAndView.addObject("city", city);
        if (iata != null) modelAndView.addObject("iata", iata);
        if (trip_type != null) modelAndView.addObject("trip_type", trip_type);
        return modelAndView;
    }

    //Todo mybatis foreach...
    @PostMapping (value = "/JSP/flight/booking/booking2")
    public ModelAndView bookingView2(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("JSP/flight/booking/booking2");

        BookingVO bookingVO = new BookingVO();
        bookingVO.setJourneyType(request.getParameter("flight-type"));
        String[] airportFrom = request.getParameter("airportFrom").split("&");
        String[] airportTo = request.getParameter("airportTo").split("&");
        String[] flightDate = request.getParameter("flightDate").split("&");
        String numOfPassengers = request.getParameter("numOfPassengers");
        String seatType = request.getParameter("seat-type");
        for (int i = 0; i < airportFrom.length; i++) {
            JourneyVO journeyVO = new JourneyVO();
            journeyVO.setAirportFrom(airportFrom[i]);
            journeyVO.setAirportTo(airportTo[i]);
            flightDate[i] = flightDate[i].replace("/","-");
            journeyVO.setFlightDate(flightDate[i]);
            journeyVO.setNumOfPassengers(numOfPassengers);
            journeyVO.setFlightClass(seatType);
            bookingVO.getJourneyList().add(journeyVO);
        }

        BookingInterface bookingDAO = sqlSession.getMapper(BookingInterface.class);
        List<JourneyVO> journeyList = bookingVO.getJourneyList();
        for (int i = 0; i < journeyList.size(); i++) {
            String from = journeyList.get(i).getAirportFrom();
            String to = journeyList.get(i).getAirportTo();
            bookingVO.getProductList().add(bookingDAO.getProductVO(
                    from.substring(from.lastIndexOf('(')+1, from.lastIndexOf(')')),
                    to.substring(to.lastIndexOf('(')+1, to.lastIndexOf(')'))));
        }
        
        List<ProductVO> productList = bookingVO.getProductList();
        for (int i = 0; i < productList.size(); i++)
            bookingVO.getFlightList().add(bookingDAO.getFlightVO(productList.get(i).getProductNum(), journeyList.get(i).getFlightDate()));

        for (int i = 0; i < bookingVO.getFlightList().size(); i++)
            bookingVO.getSeatLeftList().add(bookingDAO.getSeatLeft(bookingVO.getFlightList().get(i).getFlightNum()));

        request.setAttribute("bookingVO", bookingVO);
        request.setAttribute("dd", bookingVO.getSeatLeftList().get(0));
        return modelAndView;
    }

    @PostMapping (value = "/JSP/flight/booking/booking3")
    public ModelAndView bookingView3(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("JSP/flight/booking/booking3");

        return modelAndView;
    }

    @PostMapping (value = "/JSP/flight/booking/booking4")
    public ModelAndView bookingView4(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("JSP/flight/booking/booking4");

        return modelAndView;
    }

}
