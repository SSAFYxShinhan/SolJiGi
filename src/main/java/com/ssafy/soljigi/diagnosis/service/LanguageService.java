package com.ssafy.soljigi.diagnosis.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ssafy.soljigi.diagnosis.dto.response.DiagnosisQuizDto;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LanguageService {

	static final String[] srcImg = new String[] {
		"https://lh3.googleusercontent.com/fife/AKsag4N4CywlQGFMe2tVM6uUGhj40R9njqlYGdgp870Sq6FOCVBCgJBHz_LF9BKlimQw76r-bUV6Id9c5JFxIEBd5ZSp1fUO6H1Lb8cDjaqMi_vGfK6w5TLbFn6-A6mafqotDpJMr-jMMtcaHC__UBfEIHKxlxNVxI0EqZehlg5lKHkytod8KLm5Db4MdrAvyIV7D9HK7cU4aKBFgMGEM2PeThUzdf1hPeJ33vG2lAPEgNB3i7gwX5V8fwQH9GkTEqvk7ocub7V1ik2QwnZPMe8EBTrB9hAgAHYYXLlV-SC3Xpjs5X_t_O1iRFJlBpfYcDbtGOw98AMtwRk0uW52PS20yYevrXWPZRN7pil8Vo8AQp_t5qcUN0REq99I9tke5oJBziBdem94-jOThIwgvyTPBxsMkwznpLyGHdQ1PHscquJFHACLSY8HPhZ_73QS1KnDp74qHQx-qMCc66ztIN0xk4mQuAJNkPcaIi2uCCClFMCr9KzB9Pr7FJYbo9oYwII30b_lPbPQPS6UUVOTlwGbfPfgSdOu_-12E76hYVxJKIOZ2vWIdke7odo6R-delJhq0gWj0C3emGmoam7ocdwQ48R9-xc4_pIyhlftXYlIvfN8SsVwfAtaW8TePyMsJjY2b4W9yTEywvCduiPEqooaADuIlYPFhQe1VjLrUUcjPM78VJc_Wqoc8idKh7XEklqn5U7MSXNFRwco68jdjg1uaCPV1xg2WsDClO0DerH-dXyCq-EINPZ8V1zQ435OAgDV1EU-KNVgktBrQpR4W-lJFJmqUvTpZ1iYLuw5xZ3jh09EqyPyNTaAWK_6LdooMnJEaNiOv7Qq2Npk73avQVkgi-eeACeoZClCTuydWd8FFR9eY8FX7PVDwamMvrN3Lp8dDtPismis831amV-qXf-byqD9rw9CqLMqgvsC84epQ_zZ7s_1IdN7PTx0CJBMiebKjHgcPkbfvld-6rhPfA9A5WjMBJV0iLTQ9f0hbzfaT0HXZ-kODqYU0HYsvv9wHD1-_c-bUJAsPVTig3ASo38STIcbhpU8izwCpMM9L5AjIfetHQyVSn7zyZyMcv5pO4Et4_6k18xiK9rRhG72PLuUp1Lgft7-miCcqSpVwSMS4Exk5PSKgdlTsBH6b6pnZExXJC_DyM03XMoc8hM3Rff5zo3q9wK-XbnjtKGsGzlmXF5fkJoxLvUr5cDuJDmIwnYz6ZkKJrmipjhpKAV8LpXiJQONyl2jx4BkdVod9WOnkogxtQz2mB_4vIlC5TmZvGKIJU2lWRM7PRDKIzYQiDT8Fkzwwd7gEbNenRI7q02Kw4JLlPd4QkLyCDSOGS1pkx19qt5h-vGvCOgwaHm-BoQzUReVvcib2ncILuBKIY7nGD9y0sQ8hquNPZCZ9jEbr2L5IR59oogIIPvctp2VMJiPa8uN-5Q0hLKwGnIWGjX8wusvquYkpBjPIRAwe5Pvl3sHBIotgz5cUIqj0Enu5Fs3wV7uVhnlYZGLHKj7OjJElk3vLzIGuPhNhqwTHyX71-GzeTAdTrVXbSbDGfWeyKVi=w1920-h963",
		"https://lh3.googleusercontent.com/fife/AKsag4Ohi8ZVoJi182tLLKTreDxFzILdL6_7yVSvX9y9UmDz6MTT5unSdYUq0xae22BLQ24uZNGHgs4kSNoWEcJAgbPMI7eXRHadiZ3eJHp7ofKoxxmULwq6xU5gyIC4McmZjnfPPGcJQz5UVahoPnO9J6Migr8euLdcnTToqI3Z0RJVOzv53GMlV4LVdsvG_OQKgF4YVLFlRJo3pAlXvshSzUJ6kCFjoFGWmTe5uZyiI__qzMpsrw1mcwFpDVo-hql1qvxCyeYwwIC4RnBDg4bK6FdDBk_4F0dbHXouOde8dOS4VydeoyzWXe-wgSkQ4sC3GedfKvxBz58sGnz7bOH5M-FC4jUQ0AQzJJZsfFe9cSYbKfaPU6iwwtT6hHIqAaceCO32ITMKKoAoxmTGAaAPeH7x2dMUeXPKaoAk2MJz1heb-iAMHLdMjuFGxvhteHaROyaM5B9dG_LwOj8L4LWaTKHdBf4-jEiZy9OIFNLt22apE6fsw8mO86JGGe4RqYKOhxYadNb3f0c8G0XNfegNVpee0agrZytMtxgg2SXnoszW37zHvTNeuNSqzgh7FyapQxnh37hfOv1NZAKwBGWY3qdv5C5Xi7QIDWlvxc7gTV5h0A3DbFFQM4RwSQcABsIJppi3yRxRsVSWiI0wvPAZYUcTPfPVjMrKYpfWT8-YIF3BT2urTisxwnxGlFYWucXu36rexYjuC8LbmRlKyRFPQZOLo4siTcl8X_GrrXvcL7vP8tRqVklc36HaWQpp4ChLlT6uupDFk5_f6YMaL8XjMw-vFkzoWGmS2x_IkgIaiUNhBseboih1KtHXGJUjxQp7MEdDNtDol4AYbNXkbYpAHI_j8bXQsbBrCfW8raVezIfJDvsDblvLy1V8euPUFQatMQsX4_veKSEX3B2LjvZpuX64lc1UbGMy8r_dbtXO1Myy6_MSOySk1UMxTla2bAzEdHns4qjhIbxx7XZfPybXXQ9834s7wSI03UnT9R7peGUK31UeOQjTF2wjnDMuoCIUHEZ9sXwHlPFrii0uO4ruhFKkHQOUKe2828wWbHrX-_poqa6Ig1uCXM0dWaLJZZFwJdm1bZ1h3fgblq5xQKIj67oqummftPO7cg3b4ifJUEPNiP56IlPqzyISkJQfs1F1RTDnr71C68uDw6nJ9UMdKKNB7Q1QaK3slyT6dUbyBka1OCHBrQ5d7lNc4qBDOWSHHQHNcy2pbH2RndykmsIx6lVdYxqbY2auUcm03J09QewPQQDak2PTC5pWT_HZ5x_SzLj9bbKLRi-LyNzt0UeM95Ao_Csq7wN7RDUV3diLUfaajmbzNTFfcxprh5MCZQ6tMMKqOQ49AAViQ58eL1y5HZTRU4amzjQDnNLSpBsgWkKabXCaZQHNNWQosAhcSVA3GyRh74J8umQkN69jtlj3wdVf8RZogtbfrgOT7IpM_hq_-dY1-OP1p1Bm_Qgt7GJJ-Lozybdcnb3t2lum7Bg1Dn5D_Eic10q2FazIlMnH3lSRMdoHjTdwn3JAFZw5QHLGgOlpUAISxRoSX6kQWu7L=w955-h963",
		"https://lh3.googleusercontent.com/fife/AKsag4PZs7Rjre7iQfqX-qg4Vid_YyPQBzG2AHcZzsL7tpbEiEPMyQIarMglAUWp5GYczDHnHWeFcGeAzKgsG78IlD2pJwZ5BW8BGU0Lywjukbz9gyFxNcc4ZNJ4dpdHcafRoMhrvmGjctLugC_VS9Izztfu5VMKn69AaNf6v53mbBJt8yElK0JEPRR5XMBult5JFxQGZkmC4ttds8IsUBVQ29XaYKiIqP94y8aznAsyhq3XlR9RwpqhdEk0g-rZ4O_6vEN96bby18VdQMol7ahKAOS047g1_kzSr4I9OEm-aj2sub5qg0CPAysJ8_roMCSM28aiWHnUWF_OItOAju7-tySgyV59K5K1NHSKNHA2LdaTsO3giyX5gkujWCY-2-CgxtvlVhh5usnRCqtF1xr8iMVBbr_bKcH-Y09r_7Du2NW2I5HwykqRQiVc7pouhSMJ6_yepIiPZhO_Q-ITP2i6BC18f4uxp2blL_wEA8yzdmmmM7w15yKW8KGXD474eHgGEbiUaMvk2baU-vXEptWzcFh6j5y7almVIo2lAjKdnoIO6rkOGEzGS8DTT_aMWk4nAVTjBeWgITS3MQztcWEajsk8IPt8EyttxJcOI284PHhNEXuJp44VdHWEoQ5EeHMjlyNkd5T7sXn4S5fOh4gK42g2muelhGxoD3ZhgtjuFsFXntZdFddsMzcLE0a5jlioWrXrAuo7oGMc9MufrHTzP_ebl_Ge16QQ2N273KJ18BcTNDZfAzm88wxQfvaS1fjTUM0jP0IqMiTxJlWd_Jwz2Iqza-ql230O9QDXHTgFRktDIjAHqTOOxNCk4k2rsC0rf156wK8k_XbnwhlFNjgzE8lZF4cqEh6wdvEL4cy8a8Z0MVFEpSV4pGatZBimqg1I0BJu2hxCfVukuY-8ljMT-UAtRAuDPrVAfUnT6VKMLBt4PLI01SILgD3Ytplo2nSSzsSU9iDr5KxpGRe9jnLe2lgUjcslumwDFRgyqNfSEpBO4Nt5fnV-FMPK5ZXFLa8MAlgCulZW16dIB0oxgLuOh595xZr6YlTOF86qetcwZ3fyg78AValvsilUCa_dgP2EZrEnwGaylRIxyK2uC8Md6WJBrMR-c1rMULjTgWaoA5YuEnrolXfFHnmP0s8KdBfOFuhev_Ksx33Gm5jBhFE1IRL04U_dppnmdCt-AinI4Fd7X6xZ5g5rhq_GXHOxpZneFIlOj7qH-Hb8jM5eCv679x8cCZmsbjFPjZUyT_6zjDY-Xm3NLuFY3zjVitERPYKrt55geZWuhK9pf3roa0CcBJlG5wwABj5UpYnEuqWMdj8gUi3cQequlSnfvK8rbYxPgqukoeOFM_kh3TrekmcQYKOZx_VjF6InVpI1VIQfenN1pDdwpztT-NLWpQSVV3efQTLqKh-rbC1lQGQNCyIS2L7qKtD_2tNtVnZsJEjfnq5hZXBQoux8ljZDk3SN6L8g3S1UTKbhKtyAjseYk38se73REsrHLLp-c6gxj4Q2NW4j3-kjOY4KbkiX6uoRSP7eKEyiu8nJWd_JhAxMqGXX=w955-h963",
		"https://lh3.googleusercontent.com/fife/AKsag4NmwXx0cBh648NVWl3GvGafLrS6BA9PZsNAM4Wp5fFX1BxNIqWHPuJWo029neF5BvcRNZs2gpJ0eXYw3NC0SO5447ChAHDm69jOCJkQ56x3N0sjHefxo9YPpQLbesMwxbUuWa9WTTbcqxEzv85aJfU-87lFKyPmuqwNthJ86cFHDX8j1BhaLRqtumYw8dQPvohoJRP9wzZEPRUOIvF1KFZpNfG6rMMxthHU4V9QMhM8STbX39y1fDLk2Y-LjQK4Ry-D7qpJHaBHzd72fC-4s1wY3DGPW69pZjX0oqk71GbWhB1ZSFjkZEaOQjkcsgEf2F2oxXc5n8yFjIempvRvQ1JRt-p71WlQuehRIKWJMsy3d-gX8FokgR5gCAZz4hNziEDi8WJjCUSInvF7HKbR_lkLlF1w8f9bv0SXFJCaeugVjUp_hYwkWF8v8npBtogruZo-o56UVwyFMm8Pr0tqnS5nK8MTuBhMVJwsDr1tps4_Xp134l99cFJcrQlPL6Txh7o-lkMD7ybYQQKpamQyNMr2ekmzgT5W_PAne3hbJ-GiJG_kfmxikR9szHaonJTsWRf_mYecigbzFANu1LNCgi6qydmUSkLmirCc8eaDKGK0CYfvSfmjTn2s2B9Fa02QVRpyI19xlZZ6aAi7R_iplZTnBopN9gctsI1zRzBsmBAmf8oTvJWMLD8MPUVb15Y5GkdxOIcU1z1EFn8IXQcoE41mb5QBWLiBp25sahcv4h-xNky1MTopGuxNd-deeWYok6IljXcQQLsBKAcLSbGsi7E2ZUz4SVcX0-ACwPiKzyaLJh7EBSJfVPnRvYEGNwqDKN1gzLZQiv1fPZWnXqI2N57ogkwLExWURR92qWikEVHwWYzZoVlzOfmHvkWG4QhPSszOrOFJ1xoXyV42mJN4tzqAHut1ZziVNCTO8u00GDxtWfAh93pSA-_bZoYngaSA3KamPK5KrU8vSaLmahTP-sBtmjCOvyvJtRph1aEa8fHzlusurio9N_CD1X8kVyfh5npK9To0AmLNfA8ARKN1RAPaxfrjJRwH6lmJjEPk9mISr7YQP5NidrzGkZpEqPatTTz88oO4KU_zRkgW_hn0aWhA3jr6XAsep9NN6p6YdgrCFfgRNkD8iCxKfP9lyi9e-I7IlhIHkG_J6f8D81MzqHekX7xzO-M8ptmFYwsUBcK6HpIS1Hi10UHcHWQ7NbGcY9BAuR1da-vuBOszFEz_MBPdKUBJ6XiMIkgp5rZEmHFNlWlYwsQWNgf2mcTCArG6hPm9PtLK3gaFsq6639hMcOCShXoVGwWAnIl5-pRaY-b79l5dzq3aa_U1WyIvoCGJxMd0MbC0OGvKrQ1mreVT0lFX8JYRseFpU3VLLiSul7VGw-wvEHGwh2Fjr-qbHmrQCHeB5yhD66FJgKIlYdxVn6OgihqZepferZzMe0NCg_K-gJTGYOUqusbdKOHXCsjSJPDv6MgQQR5fAkRDpKhG_MnH3kSnCG7lIZ2S2QJKy4d3G4ASPctGscjahxCJXNdjsV2yr5sCxEcQqH9_eFWI=w1055-h963",
		"https://lh3.googleusercontent.com/fife/AKsag4Pk4BEb3FZMx9MJZnDWvMAF3VmWuxmxzzAjy06BtvcBwo8RlcnzRac-P7SGZxw1_1OIOyOXNZFKw68iQiDgURNgwdSoOclhXS1oBMTn7PnUSJrhFLgilWnEpqTf4MJ1eXVGOJkhrRLXK_ZUc3rG_swIfAmb__TadsOcPTZshyof91hAqrcpFJEjIWWAbC4jLcxjydMLk-rS-ycVojhFB984ojiYVTW5XvyKDz3xuTfjHXA3miHlmYzMqPFHsA1eXTzRHADogv7MiVS-ATT9C3_zbH4IFYxN6JIzCcDaaS_TRvbG6qqzboimWZK4CJU8YjG-JSbUHGP7wM8zap8aksO63ugDYbtNhUJEQjcLwtTNQGL9t-LsG-cnypCeAABS1_cXXOX6IDxbXXIJh0EXbhuwngRaQkN_fpV0QimDh32eYqNTSiEpKH7Xx9GHMr3S90XAT-yNRExY-p4XG1k_0QPcP5LKKAPiRHbnvh-W0QZC6zPV3eNPStbGqp3BQ_i43c132nMr-oJ0mdXZ3cyPLevtON12C2sI1o60VEqCY9HI29_F4wg7MHuVCKo31-CdJ7_ytvyitwDHuw605RXZRxx8bWB8Wlj7NkZTjVTtfOD7PmalTTIDW3hcP5chuIJLpYpJPKa5KgCpVqooc331zpHveKIrogwOUJjWC8rLo9TW7KOv-P_0dHydehd2eGEBjWzu0wnrt2CHlPs3oNItDUXGvCVmaOD8WLNhpA47GQKfROtqQempZwSEzOdnZrciZz8mdLtx7h3Vz5ASpUAscNE1HT1YDGwOjopwbTL0jfzDsQksEe7CrZ36Il-I7CchaJ5REI-lkp5ZWkbtTSXd_c-BX4FpnwsxI5T0PluuoOASSpnWiG5MpF7ev0p1x-8scQBdt8I6PkuFYsSsuWAIGBrvYP4XszpoXQlvBg96LhRWV4O7v9fJf-zlVuvo-ukSJ3OIEQd76CnayeJRGW_Ii_qLfe5aAJmX2CnHt3rzmk0pjw4OdYOdy7Td4_uiSsjGLuLsdXomTy6CQ5febons6D_Z2gM9JE_Y-LaPIfW7u7IjXiQEVDGxFF4PWY-e5x2GGvYoY_7IkvTnQxHR99pwcPC4k0ylmwYUlKCh2xtMRpXacrGlHPaekXD9jMRVtprORxitBYajX-EziCjpIWqFwQxi8iU-H9N4hjRTXE9ROLtVZGzjLfdFHENCNmeGBY49Zl81pfvrxjexIdWAAZCMlMXZBmlBl-DOkZaFmWCcmaBvvNVgODwBFeM3bFHQUEBKYHGT-BYSu7MMNkIT9Icd_QN9P49nvosb3BOGkKOz4Lx9z4Yp2xH6o8LOvu6nBjP3GHbvdx2u8AU03Q6eTNxKwWHeGBjdgIeJEONXRRC_zm0OHmingy_AAPOmvWP391FFxiONag0fhucVpD10yw9eSzVLYlQKXu7tAUAojZa2j8C96S5s6P7HupLLpGdIiUGCYMT5Xx2IgS2EScuQVN_Z4DZrM9pMaiuBoLDfNwcHU9L70GSwdeReo6oikq22yzZmxC6KsjS8M-bO-yjSvoeG=w955-h963",
		"https://lh3.googleusercontent.com/fife/AKsag4ObllK3Gv9sLbVqgoCYZt1t6cHh3chbxTaWWv3HNJDJTJ4D6p_n660mXS5PS96W4laNylcU3ROcub74XYMFeN5mRtjZ1Y7f6Q775pB88rdCZRYwsiFIGwFdz2P4qr0JjHujmDrPzMA1YaBlMmkJmno3hXRUJU6jvylUnQfLSuSNod_ZH_JZV-g49p6NDc1cETNaeGs3wxXFgVsZ5wiBS6m_YNRg-d3mXKiJjiO6j9YAViIT8dgvPu9AQ7byQvwwY56LylvxKK8GeKuLODjh8tlKu1rM4VvMwVTjepuluGJMqmbM-soL3r0UYXSryGer6e5CwK_OGBo0q3MZXtFDPNEfLD1Utr1f5ucrC7INXsTPnAHNapbpFWHROh3HThkgCl5WEsaxralmszCmvSdCjJW-L8HabssnNHbRp6LbuW0dAgMe7OcavEMWYEesrSmWNBnE1Q4a-62RR0P1t6JtlZ6Jc3hwR6W-E7dFkmfWIRc2jbGLamQReXawuBCuDIYlVJ_A7Lg2h8iPQ5DkXoxNE3CEzcSztS2WcjL25inZDfzOtZj5PeksQ4AlVgrikEVdL-fF81-glsaSxXFJ4rqtg8RXlnfPtuYV0e0i4TZojV5stGI1IyceheWbakVx8I3WGH0lWPrI5RfWjbkOv65iji9S3moqsFWY8_HQnA2i2V6wGJTfD3G0ZAJEXOy-OelSDf55JslpTnqE4laBFTUZUFYnxybE29hvI_Wx8ifJBVslAU0ud7y6kliEujnhiCZpyMr8UAOp_85RRnklzwL-biRzkUffLjH0Sk78HZvyv7hlqK0xgs4qvDeLV21Q6aGKkQAualInZPr6GwGwA91JOyZCfKdV_4PQhVJlO4tWr3y8HCHGziyuvnqoAvT8DOhlASt5zpBeNdprS06VM67xuQtYlJskZsbNvQjjySi1sSMpChbTwPd7A7GfCUqbkxwxj_jmxrXewfuAEZriGknRcHH6d60IkiipQg8oDnPerAcBzY6YOiCxQR3GnnDF-4OaSS-FMN8vo_pcytB-bbS5i-eGgzheG9sxOTfkKJcDmtyZdBhGeLjzRqrDym33FBG66kZtaMqi62AxDnWv30IT3Zog3XZ3wyhaqswVt_5CR3IzYvNbdG4DEi7NJLlQXtFUEqdw7CsbWQbRo3FWYYSC1M50jZthFks2nFusp3q18hWLRm_TcQA9azPeJHpaVEbd35bYX-sR4RunUK_bgprcuV8i4mQPGZja1wIHILOp1am9ru7QwVSzVrS_Q84GPXXdkQu0JQDQjZeGNTq-bDhXa01FzY-6qs6m6YVb18lQVPVWLu3NHrgY0vq4G5Dl5MLPyQ58qtjCpk7ACxVjvIBzoj30FqITZqvg-X52tN0sX8ovs58L8TkYEd7OdcXqMzz_7MY5POwiymJ_LTqXyk2BpWHOn4Q1__qaPMfCah2d8bij860XCHzGtJlPRDirZw9BgDcl4g5BoWOM7JJVmlquDKk5W84Q-ynsBbthU5RdqAFC7llexbgzqo3IYBi2JBUJUxpDlmWOHbBSLzJ5-5uS=w955-h963"
	};
	static final String[] answer = new String[] {"바나나", "시소", "칫솔","비행기","사과","태권도"};

	public List<DiagnosisQuizDto> getQuiz() {
		Random random = new Random();
		List<DiagnosisQuizDto> quizzes = new ArrayList<>(3);

		int index = random.nextInt(srcImg.length);
		quizzes.add(generateImgQuiz(index));
		index++;
		if(index >= srcImg.length)  index -= srcImg.length;
		quizzes.add(generateImgQuiz(index));
		index++;
		if(index >= srcImg.length)  index -= srcImg.length;
		quizzes.add(generateImgQuiz(index));

		return quizzes;
	}

	private static DiagnosisQuizDto generateImgQuiz(int index) {

		return DiagnosisQuizDto.builder()
			.type(DiagnosisType.LANGUAGE)
			.question("다음 이미지를 보고 무엇인지 말해보세요.<br><br><img src=\"" + srcImg[index] + "\" alt=\"quiz_img\" style = \"width:250px; height:250px;\"><br>")
			.shortAnswer(Collections.singletonList(answer[index]))
			.build();
	}
}