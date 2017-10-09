package be.vdab.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import be.vdab.entities.Land;
import be.vdab.repositories.LandRepository;


public class LandServiceTest {
	private LandRepository landRepository;
	private LandService landService;
	@Before
	public void before() {
		landRepository = mock(LandRepository.class);
		when(landRepository.findOppervlakteAlleLanden()).thenReturn(20);
		when(landRepository.read("B")).thenReturn(new Land("B", 5));
		when(landRepository.read("NL")).thenReturn(new Land("NL", 6));
		when(landRepository.read("")).thenThrow(new IllegalArgumentException());
		landService = new LandService(landRepository);
	}
	@Test
	public void findVerhoudingOppervlakteLandToOppervlakteAlleLanden() {
		assertEquals(0, BigDecimal.valueOf(0.25).compareTo(
				landService.findVerhoudingOppervlakteLandToOppervlakteAlleLanden("B")));
		verify(landRepository).findOppervlakteAlleLanden();
		verify(landRepository).read("B");
	}

}
