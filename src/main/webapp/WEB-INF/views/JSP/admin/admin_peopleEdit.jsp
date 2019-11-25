<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="adminHeader.jspf" %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">상품 수정</h1>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-lg-12">	
                            <div class="panel panel-default">
                                <div class="panel-body">
									<form method="post" action="peopleEditOk">
										<input type="hidden" name="peopleNum" value="${vo.peopleNum }"/>
										승객타입: <input type="text" name="peopleType" value="${vo.peopleType }"><br/>
										할인율: <input type="text" name="peopleSale" value="${vo.peopleSale }"><br/>
										<input type="submit" value="상품수정"/>
									</form>
                                </div>
                                <!-- /.panel-body -->
                            </div>
                            <!-- /.panel -->
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="<%=resources %>/Admin/js/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="<%=resources %>/Admin/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="<%=resources %>/Admin/js/metisMenu.min.js"></script>

       
        <!-- Custom Theme JavaScript -->
        <script src="<%=resources %>/Admin/js/startmin.js"></script>

    </body>
</html>
