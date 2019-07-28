<%@page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!-- new modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">New Content Provider Define</h4>
            </div>

            <div class="modal-body">
                <div class="form-group">
                    <label for="cpcode">Cp Code</label> <input type="text"
                                                               class="form-control" name="CP_CODE" id="CP_CODE" placeholder="Enter Content Provider Name">
                    <Br>
                    <label for="spcode">Sp Code</label> 
                    <select  name="SP_CODE" id="SP_CODE">
                        <option value=''>Select</option>
                        <c:forEach var="SP" items="${Sp_list}">
                            <option value="${SP.SP_CODE}">${SP.SP_CODE} ${SP.SP_NAME}</option>
                        </c:forEach>
                    </select> 
                    <Br>
                    <label for="servicecode">Service</label> 
                    <select  name="SERVICE_CODE" id="SERVICE_CODE">
                        <option value=''>Select</option>
                        <c:forEach var="SERVICE" items="${Service_list}">
                            <option value="${SERVICE.SERVICE_CODE}">${SERVICE.SERVICE_CODE} ${SERVICE.SERVICE}</option>
                        </c:forEach>
                    </select>        
                    <Br>
                    <label for="cpname">Cp Name</label> <input type="text"
                                                               class="form-control" name="CP_NAME" id="CP_NAME" placeholder="Enter Content Provider">
                    <label for="esmecode">ESME Code</label> <input type="text"
                                                                   class="form-control" name="ESME_CODE" id="ESME_CODE" placeholder="Enter Esme Code">
                    <label for="srvfor">Srv For</label> <input type="text"
                                                               class="form-control" name="SRV_FOR" id="SRV_FOR" placeholder="Enter service for">
                    <Br>
                    <label for="Package">Package</label> 
                    <select  name="PACKAGE_TYPE" id="PACKAGE_TYPE">
                        <option value=''>Select</option>
                        <c:forEach var="PACKAGE" items="${Package_list}">
                            <option value="${PACKAGE.PACKAGE_TYPE}">${PACKAGE.PACKAGE_TYPE} ${PACKAGE.DESCRIPTION}</option>
                        </c:forEach>
                    </select>        
                    <label for="Stream">Stream</label> 
                    <select  name="STREAM_TYPE" id="STREAM_TYPE">
                        <option value=''>Select</option>
                        <c:forEach var="STREAM" items="${Stream_list}">
                            <option value="${STREAM.STREAM_TYPE}">${STREAM.STREAM_TYPE} ${STREAM.DESCRIPTION}</option>
                        </c:forEach>
                    </select>       
                    <Br>
                    <label for="startdt">Start Dt.</label> <input type="text"
                                                                  class="form-control" name="START_DT" id="START_DT" placeholder="Enter start dt.">
                    <label for="enddt">End Dt.</label> <input type="text"
                                                              class="form-control" name="END_DT" id="END_DT" placeholder="Enter end dt.">
                    <Br>
                    <label for="sharingtype">Sharing</label> 
                    <select  name="SHARING_TYPE" id="SHARING_TYPE">
                        <option value='Y'>Y</option>
                        <option value='N'>N</option>
                    </select>        

                    <label for="sharentper">Sharing(%)</label> <input type="text"
                                                                      name="SHARE_NT_PER" id="SHARE_NT_PER" placeholder="Enter Sharing Per">
                    <Br>
                    <label for="afsflag">AFS</label> 
                    <select  name="AFS_FLAG" id="AFS_FLAG">
                        <option value='N'>N</option>
                        <option value='Y'>Y</option>
                    </select>        

                    <label for="minqty">Min. Qty.</label> <input type="text"
                                                                 name="MIN_QTY" id="MIN_QTY" placeholder="Enter Min Qty">                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary" onclick="saveCp()">Save changes</button>
            </div>
            </form>
        </div>
    </div>
</div>

<!-- edit modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Edit Content Provider</h4>
            </div>

            <form action="<c:url value="/cp/update" />" method="post"
                  acceptCharset="UTF-8">

                <div class="modal-body">
                    <div class="form-group">
                        <label for="cpcode">Cp Code</label> <input type="text"
                                                                   class="form-control" name="CP_CODE" id="EDITCP_CODE" placeholder="Enter Content Provider Name">
                        <Br>
                        <label for="spcode">Sp Code</label> 
                        <select  name="EDITSP_CODE" id="EDITSP_CODE">
                            <c:forEach var="SP" items="${Sp_list}">
                                <option value="${SP.SP_CODE}">${SP.SP_CODE} ${SP.SP_NAME}</option>
                            </c:forEach>
                        </select> 
                        <Br>
                        <label for="servicecode">Service</label> 
                        <select  name="EDITSERVICE_CODE" id="EDITSERVICE_CODE">
                            
                            <c:forEach var="SERVICE" items="${Service_list}">
                                <option value="${SERVICE.SERVICE_CODE}">${SERVICE.SERVICE_CODE} ${SERVICE.SERVICE}</option>
                            </c:forEach>
                        </select> 
                        <Br>
                        <label for="cpname">Cp Name</label> <input type="text"
                                                                   class="form-control" name="CP_NAME" id="EDITCP_NAME" placeholder="Enter Content Provider">
                        <label for="esmecode">ESME Code</label> <input type="text"
                                                                       class="form-control" name="ESME_CODE" id="EDITESME_CODE" placeholder="Enter Esme Code">
                        <label for="srvfor">Srv For</label> <input type="text"
                                                                   class="form-control" name="SRV_FOR" id="EDITSRV_FOR" placeholder="Enter service for">
                        <Br>
                        <label for="Package">Package</label> 
                        <select  name="EDITPACKAGE_TYPE" id="EDITPACKAGE_TYPE">
                            <c:forEach var="PACKAGE" items="${Package_list}">
                                <option value="${PACKAGE.PACKAGE_TYPE}">${PACKAGE.PACKAGE_TYPE} ${PACKAGE.DESCRIPTION}</option>
                            </c:forEach>
                        </select>       
                        <label for="Stream">Stream</label> 
                        <select  name="EDITSTREAM_TYPE" id="EDITSTREAM_TYPE">
                            <c:forEach var="STREAM" items="${Stream_list}">
                                <option value="${STREAM.STREAM_TYPE}">${STREAM.STREAM_TYPE} ${STREAM.DESCRIPTION}</option>
                            </c:forEach>
                        </select>        

                        <label for="startdt">Start Dt.</label> <input type="text"
                                                                      class="form-control" name="START_DT" id="EDITSTART_DT" placeholder="Enter start dt.">
                        <label for="enddt">End Dt.</label> <input type="text"
                                                                  class="form-control" name="END_DT" id="EDITEND_DT" placeholder="Enter end dt.">
                        <Br>
                        <label for="sharingtype">Sharing</label> 
                        <select  name="EDITSHARING_TYPE" id="EDITSHARING_TYPE">
                            <option value='Y'>Y</option>
                            <option value='N'>N</option>
                        </select>        
                        <label for="sharentper">Sharing(%)</label> <input type="text"
                                                                          name="EDITSHARE_NT_PER" id="EDITSHARE_NT_PER" placeholder="Enter Sharing Per">
                        <Br>
                        <label for="afsflag">AFS</label> 
                        <select  name="EDITAFS_FLAG" id="EDITAFS_FLAG">
                            <option value='N'>N</option>
                            <option value='Y'>Y</option>
                        </select>        
                        <label for="minqty">Min. Qty.</label> <input type="text"
                                                                     name="EDITMIN_QTY" id="EDITMIN_QTY" placeholder="Enter Min Qty">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" onclick="return updateCp();" class="btn btn-primary">Update</button>
                </div>

            </form>

        </div>
    </div>
</div>

<!-- delete modal -->

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">

    <div class="modal-dialog" role="document">

        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Cp delete</h4>
            </div>

            <div class="modal-body">
                <p>Are you sure you want to delete this data. This cannot be
                    undone</p>
            </div>

            <div class="modal-footer">
                <form action="<c:url value="/cp/delete" />" method="post"
                      acceptCharset="UTF-8">
                    <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                    <button type="button" class="btn btn-primary" onclick="return del()">Yes</button>
                </form>
            </div>
        </div>
    </div>
</div>
