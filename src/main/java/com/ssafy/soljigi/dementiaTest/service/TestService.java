package com.ssafy.soljigi.dementiaTest.service;

import org.springframework.stereotype.Service;

import com.ssafy.soljigi.dementiaTest.repository.TestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {

	private final TestRepository testRepository;

}