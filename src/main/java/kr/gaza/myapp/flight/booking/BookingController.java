package kr.gaza.myapp.flight.booking;

import kr.gaza.myapp.aviation.flight.FlightVO;
import kr.gaza.myapp.aviation.product.ProductVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            JourneyVO journeyVO2 = new JourneyVO();
            journeyVO.setAirportFrom(airportFrom[i]);
            journeyVO.setAirportTo(airportTo[i]);
            journeyVO.setNumOfPassengers(numOfPassengers);
            journeyVO.setFlightClass(seatType);
            flightDate[i] = flightDate[i].replace("/","-");
            if (bookingVO.getJourneyType().equals("round-way")) {
                journeyVO2.setAirportFrom(airportTo[i]);
                journeyVO2.setAirportTo(airportFrom[i]);
                journeyVO2.setNumOfPassengers(numOfPassengers);
                journeyVO2.setFlightClass(seatType);
                String[] dates = flightDate[i].split(" → ");
                journeyVO.setFlightDate(dates[0]);
                journeyVO2.setFlightDate(dates[1]);
                bookingVO.getJourneyList().add(journeyVO);
                bookingVO.getJourneyList().add(journeyVO2);
            } else {
                journeyVO.setFlightDate(flightDate[i]);
                bookingVO.getJourneyList().add(journeyVO);
            }
        }

        BookingInterface bookingDAO = sqlSession.getMapper(BookingInterface.class);
        List<JourneyVO> journeyList = bookingVO.getJourneyList();
        for (int i = 0; i < journeyList.size(); i++) {
            String from = journeyList.get(i).getAirportFromIATA();
            String to = journeyList.get(i).getAirportToIATA();
            bookingVO.getProductList().add(bookingDAO.getProductVO(from, to));
        }
        
        List<ProductVO> productList = bookingVO.getProductList();
        for (int i = 0; i < productList.size(); i++)
            bookingVO.getFlightList().add(bookingDAO.getFlightVO(productList.get(i).getProductNum(), journeyList.get(i).getFlightDate()));

        for (int i = 0; i < bookingVO.getFlightList().size(); i++) {
            List<FlightVO> flightList = bookingVO.getFlightList().get(i);
            bookingVO.getSeatLeftList().add(bookingDAO.getSeatLeft(flightList));
        }

        // journeydate 포맷다시 바꾸기
        for (JourneyVO journeyVO : bookingVO.getJourneyList()){
            journeyVO.getFlightDate().replace("-","/");
        }

        request.setAttribute("bookingVO", bookingVO);
        if (bookingVO.getJourneyType().equals("multi-way"))
            request.setAttribute("journey", new String[]{"첫번째", "두번째", "세번째", "네번째", "다섯번째", "여섯번째"});
        else request.setAttribute("journey", new String[]{"가는", "오는"});
        request.setAttribute("cheapestPrice", getCheapestPrice(bookingVO));

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

    private int getCheapestPrice(BookingVO bookingVO) {
        int price = -1;
        double temp = 0;
        List<List<Integer>> priceList = new ArrayList<>();

        BookingInterface bookingDAO = sqlSession.getMapper(BookingInterface.class);
        BookingVO vo = new BookingVO();
        List<JourneyVO> journeyList = bookingVO.getJourneyList();

        for (int i = 0; i < journeyList.size(); i++) {
            String from = journeyList.get(i).getAirportFromIATA();
            String to = journeyList.get(i).getAirportToIATA();
            vo.getProductList().add(bookingDAO.getProductVO(from, to));
        }
        List<ProductVO> productList = vo.getProductList();
        for (int dateMod = -3; dateMod < 3; dateMod++) {
            for (int i = 0; i < productList.size(); i++) {
                String journeyDate = journeyList.get(i).getFlightDate();
                String targetDate = journeyDate.replace(journeyDate.charAt(journeyDate.length()-1)+"", journeyDate.charAt(journeyDate.length()-1)+dateMod+"");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                try {
                    targetDate = simpleDateFormat.format(simpleDateFormat.parse(targetDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                vo.getFlightList().add(bookingDAO.getFlightVO(productList.get(i).getProductNum(), targetDate));
            }
            List<List<FlightVO>> flightVOList = vo.getFlightList();
            priceList.add(new ArrayList<>());
            //가격비교
            for (int i = 0; i < productList.size(); i++) {
                temp = productList.get(i).getPrice() * productList.get(i).getProductSale();
                for (int j = 0; j < flightVOList.get(i).size(); j++) {
                    if (price == -1 || temp * flightVOList.get(i).get(j).getFlightSale() < price) {
                        price = (int) Math.round(temp * flightVOList.get(i).get(j).getFlightSale() / 100) * 100;
                    }
                }
                priceList.get(0).add(price);
            }
        }

        return price;
    }
}
