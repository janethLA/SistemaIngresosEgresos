package com.dev.SistemaIngresosEgresos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.SistemaIngresosEgresos.entity.Expense;
import com.dev.SistemaIngresosEgresos.entity.ExpenseUser;
import com.dev.SistemaIngresosEgresos.entity.Income;
import com.dev.SistemaIngresosEgresos.entity.IncomeUser;
import com.dev.SistemaIngresosEgresos.entity.Role;
import com.dev.SistemaIngresosEgresos.entity.UserSis;
import com.dev.SistemaIngresosEgresos.input.DataUserOutput;
import com.dev.SistemaIngresosEgresos.input.UserDataInput;
import com.dev.SistemaIngresosEgresos.input.UserInput;
import com.dev.SistemaIngresosEgresos.input.UserPasswordInput;
import com.dev.SistemaIngresosEgresos.output.DataTotalOutput;
import com.dev.SistemaIngresosEgresos.output.UserOutput;
import com.dev.SistemaIngresosEgresos.output.UserPasswordOutput;
import com.dev.SistemaIngresosEgresos.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public UserSis save(UserSis user) {
		
	     return  userRepository.save(user);
	}
	
	public UserInput save(UserInput user) {
		UserSis newUser=new UserSis();
		newUser.setName(user.getName());
		newUser.setUserName(user.getUsername());
		newUser.setPassword(encoder.encode(user.getPassword()));
		newUser.setTelephone(user.getTelephone());
		newUser.setRegistrationDate(LocalDate.now());
		newUser.setExpiryDate(user.getExpiryDate());
		newUser.setActive(true);
		Role role= roleService.findById(2);
		newUser.setRole(role);
	    userRepository.save(newUser);
	    return user;
	}
	
	public Iterable<UserOutput>  getAllUser(){
		List <UserSis> allUsers = userRepository.findAll();
		List <UserOutput> allUsersByOrder = new ArrayList<UserOutput>();
		
		for (UserSis found : allUsers ) {
		
			if(found.isActive()==true) {
				UserOutput newUser = new UserOutput();
				newUser.setIdUser(found.getIdUser());;
				newUser.setName(found.getName());
				newUser.setUsername(found.getUserName());
				newUser.setTelephone(found.getTelephone());
				newUser.setExpiryDate(found.getExpiryDate());
				newUser.setRegistrationDate(found.getRegistrationDate());
				//newUser.setPassword(found.getPassword());
				allUsersByOrder.add(newUser);
			}
			
		}

		return allUsersByOrder;	
	}
	
    public boolean noExistsUserName(String userName) {
		
		boolean result=true;
		List <UserSis> allUser = userRepository.findAll();
		
		for(UserSis a:allUser) {
			if(a.getUserName()!=null){
			if(a.getUserName().equalsIgnoreCase(userName)) {
				result=false;
			}}
		}
		return result;
	}
    
    public String deleteUser(long id) {
    	try {
    		UserSis user=userRepository.findById(id).get();
    		if(!user.getRole().getRoleName().equalsIgnoreCase("ROLE_ADMIN")) {
    			user.setActive(false);
        		userRepository.save(user);
        		return "Se dio de baja correctamente el usuario";
    		}else {
    			return "No se puede dar de baja al superusuario";
    		}
    		
        	
    	}catch (Exception e) {
			return "No se dio de baja al usuario";
		}
    	
    }
    
    public List<UserSis> findAll(){
    	return userRepository.findAll();
    }
    
    public UserSis findById(long id) {
    	return userRepository.findById(id).get();
    }
    
    public UserInput setUser(long id,UserInput user) {
		UserSis updateUser=userRepository.findById(id).get();
		
		if(!user.getName().isEmpty()) {
			updateUser.setName(user.getName());
		}
		if(!user.getPassword().isEmpty()) {
			updateUser.setPassword(encoder.encode(user.getPassword()));
		}
		if(!user.getUsername().isEmpty()) {
			updateUser.setUserName(user.getUsername());
		}
		if(user.getTelephone()!=0) {
			updateUser.setTelephone(user.getTelephone());
		}
		if(user.getExpiryDate()!=null) {
			updateUser.setExpiryDate(user.getExpiryDate());
		}
		
		userRepository.save(updateUser);
		return user;
    }
    public List<DataTotalOutput> getIncomeAndExpenseTotal(long id){
    	UserSis user=userRepository.findById(id).get();
    	List<DataTotalOutput> list=new ArrayList<DataTotalOutput>();
    	
    	DataTotalOutput data=new DataTotalOutput();
		data.setName("TOTAL INGRESOS");
		data.setTotal(getTotalIncomeUser(user));
		DataTotalOutput data1=new DataTotalOutput();
		data1.setName("TOTAL EGRESOS");
		data1.setTotal(getTotalExpenseUser(user));
		DataTotalOutput data2=new DataTotalOutput();
		data2.setName("INGRESO");
		data2.setTotal(getTotalIncomeAccount(user));
		DataTotalOutput data3=new DataTotalOutput();
		data3.setName("EGRESO");
		data3.setTotal(getTotalExpenseAccount(user));
		
		list.add(data);
		list.add(data1);
		list.add(data2);
		list.add(data3);
		
		return list;
    	
    }
    private String getTotalIncomeUser(UserSis user) {
    	List<IncomeUser> incomes=user.getIncomeUser();
    	double total=0.0;
    	for(IncomeUser incomeUser:incomes) {
    		total+=incomeUser.getAmount();
    	}
    	total=Math.round(total*100.0)/100.0;
    	return ""+total +" Bs";
    }
    
    private String getTotalExpenseUser(UserSis user) {
    	List<ExpenseUser> expenses=user.getExpenseUser();
    	double total=0.0;
    	for(ExpenseUser expenseUser:expenses) {
    		total+=expenseUser.getAmount();
    	}
    	total=Math.round(total*100.0)/100.0;
    	return ""+total +" Bs";
    }
    
    private String getTotalIncomeAccount(UserSis user) {
    	List<Income> incomes=user.getIncome();
    	String resultado="";
    	if(incomes.size() ==1) {
    		resultado=""+incomes.size() +" Cuenta";
    	}else {
    		resultado=""+incomes.size() +" Cuentas";
    	}
    	return resultado;
    }
    
    private String getTotalExpenseAccount(UserSis user) {
    	List<Expense> expenses=user.getExpense();
    	String resultado="";
    	if(expenses.size() ==1) {
    		resultado=""+expenses.size()  +" Cuenta";
    	}else {
    		resultado=""+expenses.size() +" Cuentas";
    	}
    	return resultado;
    }
    
    public DataUserOutput updateDataFinalUser(long id,DataUserOutput user) {
		UserSis updateUser=userRepository.findById(id).get();
		
		if(!user.getPassword().isEmpty()) {
			updateUser.setPassword(encoder.encode(user.getPassword()));
		}
		if(!user.getUsername().isEmpty()) {
			updateUser.setUserName(user.getUsername());
		}
		if(user.getTelephone()!=0) {
			updateUser.setTelephone(user.getTelephone());
		}
		
		userRepository.save(updateUser);
		return user;
    }
    
    public boolean noExistsTelephone(int telephone) {
    	
    	boolean result=true;
    	List <UserSis> allUser = userRepository.findAll();
		for(UserSis a: allUser) {
			if(a.getTelephone()!=0){
			if(a.getTelephone()==telephone && a.isActive()) {
				result=false;
			}}
		}
		
		return result;
	}
    
    public UserPasswordOutput recoverByPhone(int telephone) {
    	
    	UserPasswordOutput user=new UserPasswordOutput();
    	try {
    		UserSis u=userRepository.findByTelephone(telephone);
    		if(u.isActive() && LocalDate.now().isBefore(u.getExpiryDate())){
    			user.setIdUser(u.getIdUser());
    		}else {
    			user=null;
    		}
    		
		} catch (Exception e) {
				user=null;
		}
    	return user ;
    }
    
    public String changePassword (UserPasswordInput user) {
    		UserSis u=userRepository.findById(user.getIdUser()).get();
    		u.setPassword(encoder.encode(user.getPassword()));
    		userRepository.save(u);
    		
    	return "Se cambio la contraseña";
    }
    
    public  UserDataInput getDataUser(long id) {
    	UserSis updateUser=userRepository.findById(id).get();
    	UserDataInput newUser=new UserDataInput();
    	newUser.setName(updateUser.getName());
    	newUser.setUsername(updateUser.getUserName());
    	newUser.setTelephone(updateUser.getTelephone());
    	return newUser;
    }
}