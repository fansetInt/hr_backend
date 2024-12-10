//package com.fanset.dms.loan.controller;
//
//
//import com.fanset.dms.loan.dto.LoanDetailsReqDto;
//import com.fanset.dms.loan.service.LoanService;
//import com.fanset.dms.user.dto.AuthenticationResponse;
//import com.fanset.dms.user.dto.ChangePasswordRequestDto;
//import com.fanset.dms.user.dto.UserDto;
//import com.fanset.dms.user.enums.Role;
//import com.fanset.dms.user.model.User;
//import com.fanset.dms.utils.response_handler.ResponseHandler;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/loan")
//@PreAuthorize("hasAnyRole('ADMIN', 'CRM', 'FINANCE', 'TECHNICIAN', 'TECHSUPPORT', 'SUPERVISOR')")
//@Tag(name = "LOAN")
//public class LoanController {
//
//    private final LoanService loanService;
//
//    public LoanController(LoanService loanService) {
//        this.loanService = loanService;
//    }
//
//    //create, update, get by id, get all
//
//    //TODO: implement the methods
//
////    @Operation(
////            description = "Adding New Users Only By Admin 🔴Authenticated 🔴",
////            summary = "ADMIN ADD NEW USERS",
////            responses = {
////                    @ApiResponse(
////                            description = "Success",
////                            responseCode = "200"
////                    ),
////                    @ApiResponse(
////                            description = "Unauthorised / TokenInvalid",
////                            responseCode = "403"
////                    ),
////            }
////
////
////    )
//    @PostMapping
//    @PreAuthorize("hasAuthority('admin:create')")
//    public ResponseEntity<Object> submitLoan(@Valid  @RequestBody LoanDetailsReqDto loanDetailsReqDto){
//        try {
//            var loan = loanService.submitLoan(loanDetailsReqDto);
//            return ResponseHandler.generateResponse(
//                    "User Created Successfully", HttpStatus.CREATED, loan,1, true
//            );
//        }catch (Exception e){
//            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null,0, false);
//        }
//    }
//
////
////    @Operation(
////            description = "Change Password By Any 🔴Authenticated🔴 User",
////            summary = "ANY USER CAN CHANGE THEIR PASSWORD",
////            responses = {
////                    @ApiResponse(
////                            description = "Success",
////                            responseCode = "200"
////                    ),
////                    @ApiResponse(
////                            description = "Unauthorised / TokenInvalid",
////                            responseCode = "403"
////                    ),
////            }
////
////
////    )
////    @PatchMapping
////    public ResponseEntity<Object> changePassword(
////            @RequestBody ChangePasswordRequestDto changePasswordRequestDto,
////            Principal connectedUser // This is to get the Logged-In user
////    ){
////
////        try {
////            var user =   userService.changePassword(changePasswordRequestDto, connectedUser);
////            return ResponseHandler.generateResponse(
////                    "User Password Updated Successfully", HttpStatus.CREATED, user,1,true
////            );
////        } catch (Exception e){
////            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNAUTHORIZED, null,0, false);
////        }
//////        return  ResponseEntity.ok().build();
////    }
////
////    @Operation(
////            description = "Get All Users By Admin 🔴Authenticated🔴",
////            summary = "ONLY ADMIN CAN GET ALL USERS",
////            responses = {
////                    @ApiResponse(
////                            description = "Success",
////                            responseCode = "200"
////                    ),
////                    @ApiResponse(
////                            description = "Unauthorised / TokenInvalid",
////                            responseCode = "403"
////                    ),
////            }
////
////
////    )
////    @GetMapping
////    @PreAuthorize("hasAuthority('admin:read')")
////    public ResponseEntity<Object> findAllUsers(
////            @RequestParam(defaultValue = "0") int page,
////            @RequestParam(defaultValue = "10") int size
////    ){
////        try {
////            Page<User> users =  userService.findAllUsers(page,size);
////            return  ResponseHandler.generateResponse("Users Found Successfully", HttpStatus.OK, users.getContent(), (int)users.getTotalElements(), true);
////        }catch (Exception e){
////            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNAUTHORIZED, null,0, false);
////        }
////    }
////
////    @Operation(
////            description = "Get All Users By Admin 🔴Authenticated🔴",
////            summary = "ONLY SUPERVISOR OR ADMIN CAN GET ALL USERS",
////            responses = {
////                    @ApiResponse(
////                            description = "Success",
////                            responseCode = "200"
////                    ),
////                    @ApiResponse(
////                            description = "Unauthorised / TokenInvalid",
////                            responseCode = "403"
////                    ),
////            }
////
////
////    )
////    @GetMapping("/find_by_role")
////    @PreAuthorize("hasAnyAuthority('supervisor:read', 'crm_read')")
////    public ResponseEntity<Object> findUsersByRole(@RequestParam Role role){
////        try {
////            List<User> users =  userService.findUsersByRole(role);
////            return  ResponseHandler.generateResponse("Users Found Successfully", HttpStatus.OK, users, users.size(), true);
////        }catch (Exception e){
////            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNAUTHORIZED, null,0, false);
////        }
////    }
////
////    @Operation(
////            description = "Get A particular User By Admin Using user Id",
////            summary = "ADMIN OR ACCOUNT OWNER TO GET USER BY USER ID",
////            responses = {
////                    @ApiResponse(
////                            description = "Success",
////                            responseCode = "200"
////                    ),
////                    @ApiResponse(
////                            description = "Unauthorised / TokenInvalid",
////                            responseCode = "403"
////                    ),
////            }
////
////
////    )
////    @GetMapping("/{userId}")
////    @PreAuthorize("#userId == authentication.principal.userId OR hasAuthority('admin:read')")
////    public ResponseEntity<Object> findUserById(@PathVariable Long userId){
////
////        try {
////            User foundUser = userService.findUserById(userId);
////            return ResponseHandler.generateResponse("User Successfully found",HttpStatus.OK, foundUser, 1, true);
////        }catch (Exception e){
////            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null,0, false);
////        }
////    }
////
////    @Operation(
////            description = "Get A particular User By Admin Using user Id",
////            summary = "ADMIN OR ACCOUNT OWNER TO GET USER BY USER ID",
////            responses = {
////                    @ApiResponse(
////                            description = "Success",
////                            responseCode = "200"
////                    ),
////                    @ApiResponse(
////                            description = "Unauthorised / TokenInvalid",
////                            responseCode = "403"
////                    ),
////            }
////
////
////    )
////    @GetMapping("/profile")
////    public ResponseEntity<Object> getUserProfile(@RequestParam String token){
////
////        try {
////            AuthenticationResponse foundUser = userService.findUserProfile(token);
////            return ResponseHandler.generateResponse("User Profile Successfully found",HttpStatus.OK, foundUser, 1, true);
////        }catch (Exception e){
////            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null,0, false);
////        }
////    }
////    //    Update user
////
////    @Operation(
////            description = "Update A particular User By Admin Or Account Owner Using 🔴Authenticated🔴 user Id",
////            summary = "ADMIN OR ACCOUNT OWNER TO UPDATE USER BY USER ID",
////            responses = {
////                    @ApiResponse(
////                            description = "Success",
////                            responseCode = "200"
////                    ),
////                    @ApiResponse(
////                            description = "Unauthorised / TokenInvalid",
////                            responseCode = "403"
////                    ),
////            }
////
////
////    )
////    @PutMapping("/{userId}")
////    @PreAuthorize("#userId == authentication.principal.userId OR hasAuthority('admin:update')")
////    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto){
////        try {
////            User updateUser = userService.updateUser(userId, userDto);
////            return ResponseHandler.generateResponse("User Updated Successfully",HttpStatus.OK, updateUser, 1, true);
////        }catch (Exception e){
////            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null,0, false);
////
////        }
////
////    }
////
////    //    Delete Patient
////    @Operation(
////            description = "Delete A particular User By Admin Or Account Owner Using 🔴Authenticated🔴 user Id",
////            summary = "ADMIN OR ACCOUNT OWNER TO DELETE USER BY USER ID",
////            responses = {
////                    @ApiResponse(
////                            description = "Success",
////                            responseCode = "200"
////                    ),
////                    @ApiResponse(
////                            description = "Unauthorised / TokenInvalid",
////                            responseCode = "403"
////                    ),
////            }
////
////
////    )
////    @DeleteMapping("/{userId}")
////    @PreAuthorize("#userId == authentication.principal.userId OR hasAuthority('admin:delete')")
////    public ResponseEntity<Object> deleteUser(@PathVariable Long userId){
////        try {
////
////            String result = userService.deleteUserById(userId);
////            return ResponseHandler.generateResponse("User Deleted Successfully!", HttpStatus.OK, result,0, true);
////        } catch (Exception e) {
////            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null,0, false);
////        }
////    }
//}
