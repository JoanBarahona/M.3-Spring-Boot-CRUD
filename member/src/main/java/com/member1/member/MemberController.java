package com.member1.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")

public class MemberController {
	
	@Autowired
	MemberRepository memberRepository;
	
	@RequestMapping("/allMembers")
	public String getAllMembers(Model boxToView) {
		
		boxToView.addAttribute("memberListfromControllerAndDB", memberRepository.findAll());
		
		return "members";
	}
	
	//delete
	
	@RequestMapping("/deleteMember")
	public String removeMember(int id, Model model) {
		
		// Sytem.out.println("inside removeMember" + id);
		Optional<Member> memberFound = findOneMemberById(id);
		
		//System.out.println("find inside removeMember" + memberFound.get());
		
		if (memberFound.isPresent()) {
			
			memberRepository.deleteById(id);
			model.addAttribute("message", "done");
			model.addAttribute("memberDeleted", memberFound.get());
		}
		
		else {
			model.addAttribute("message", "error");
		}
		
		//System.out.println("finishing removeMember" + id);
		return "deletedmember.html";
	}
	
	@RequestMapping("/deleteAllMembers")
	public String deleteAllMembers () {
		
		memberRepository.deleteAll();
		
		return "redirect:/member/allMembers";
	}

	//add
	@RequestMapping("/newMember")
	public String newMember() {
		
		return "newmember.html";
	}
	
	@RequestMapping("/addMember")
	public String inserMember(Member member) {
		
		memberRepository.save(member);
		
		return "redirect:/member/allMembers";
	}
	
	//update
	
	@RequestMapping("/updateMember")
	public String updateMember(int id, Model model) {
		
		Optional<Member> memberFound = findOneMemberById(id);
		
		if (memberFound.isPresent()) {
			
			model.addAttribute("memberfromController", memberFound.get());
			return "updatemember";
		}
		
		else
			return "notfound.html";
	}
	
	@PostMapping("/replaceMember/{idFromView}")
	public String replaceMember(@PathVariable("idFromView") int id, Member member) {
		
		Optional<Member>memberFound = findOneMemberById(id);
		
		if (memberFound.isPresent()) {
			
			if (member.getName() != null)
				 memberFound.get().setName(member.getName());
			if (member.getSurname() != null)
				 memberFound.get().setSurname(member.getSurname());
			if (member.getPassword() != null)
				 memberFound.get().setPassword(member.getPassword());
			if (member.getEmail() != null)
				 memberFound.get().setEmail(member.getEmail());
			
			
			memberRepository.save(memberFound.get());
			return "redirect:/member/allMembers";
			
			
		}else
			  return "notfound.html";
	}
	
	//detail
	
	@RequestMapping("/detailMember")
	public String detailMember(int id, Model model) {

		Optional<Member> memberFound = findOneMemberById(id);

		if (memberFound.isPresent()) {

			model.addAttribute("memberfromController", memberFound.get());
			return "detailmember";
		}

		else
			return "notfound.html";
	}
	
	//---------*----SERVICE TO CONTROLLER-----*------//
	
	public Optional<Member> findOneMemberById(int id) {
		
		//System.out.println("inside findMEmber" + id);
		Optional<Member> memberFound = memberRepository.findById(id);
		//System.out.println("finishing findMember" + id);
		//System.out.priontln("finishing findMember" + memberFound.get());
		return memberFound;
	}
}
