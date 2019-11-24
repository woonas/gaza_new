<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="adminHeader.jspf" %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">제목</h1>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-lg-12">	
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <form method="post" action="allianceAddOk">
										이벤트제목: <input type="text" name="eventName"><br/>
										썸네일이미지: <input type="text" name="eventThumbnail"><br/>
										이벤트이미지: <input type="text" name="eventImg"><br/>
										이벤트내용: <input type="text" name="eventContent"><br/>
										제휴사: <input type="text" name="eventCompany"><br>
										이벤트시작일: <input type="text" name="startDate"><br>
										이벤트마감일: <input type="text" name="endDate"><br>
										<input type="submit" name="allianceAdd" value="제휴이벤트 추가"><br/>
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
