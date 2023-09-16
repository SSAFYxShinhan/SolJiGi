package com.ssafy.soljigi.diagnosis.service;

import java.util.*;

import com.ssafy.soljigi.diagnosis.dto.response.DiagnosisQuizDto;
import com.ssafy.soljigi.diagnosis.entity.DiagnosisType;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExecutiveService {
	private static final String[] keywordList = new String[] { "과일" };
	Map<String,String> map = Map.of(
		"동그라미", "https://lh3.googleusercontent.com/fife/AKsag4P6WbL5Fx8CkY66pocwH686KkiyEHEBS-HLtqOB3J6A_Fm0lFYVyf3RdA17Csbj9l21VP2kd2FqHjxS9VshyHAfNXTlu0ecBZ-e3voD-wxnGOI8mST5TJkCV7CKTjQNdDS5j5A1EhnatOrcna1hT8EuqYv17oDvK9ZZHX40Cmm2ZUaSwZFTuWFfamhlQwwfsPcMPPLI7a6_p03IdJsbAzirWALD9ln6INFtvmXzBkU5kilcKdVuH8_DQw4f7zayRaIz2-8nqF4LHa3lYufst9Q95G1WJF0kFTvxTAyuifbMYYdo8qu2SOs8l3zZae6fXqZe75_mRTf-gW5gDJetEiEuqkDxJbwzhLhs-pm_QS9SqP8IMqJZGg9oMxiH7-c5KIRypz4G2pDGDjLwt7BfhtRI5B0rz2fpyW55VF4oozYSHMH1RLeIHQDpu3nXAmOtMUpA8pKDKK5DNG31VjY3rXWdXkCujzruouCYdWDYQDwlX78gPEjc3Acl6TOt1WyfZ51qH7s8rdvzxEST-CpHGFakizmFsS32etT9aYLWUeR-S79B-pcWZmP-UOiKBp9w25tVv_09WS1igOnqdU09kT0UAFpoRhaSZXP68ZnZGISq0YbZaP3oUF8y8T5XR4l5mvjr0NeoRC9U0S84OwIjbAgrnfb1G0o87y6lNhvqd7dQb02uQIZocXmj9cdC7gM8PQ6djfayOYVzbgWyqEM7QhsRd2ALzXAnBUIdcfFmVcKFdg3YYo0k_FghsejDuAaOqQibDL0rqE28uw_1X15CPrdEtPhg0atoPQajhGiM5dhgXsT6NdwRkua3zir8xZjUiy6tlNze2xCSpxDz6LyEE73CdvtC9y3lSV-SPk14kSJQNauz4oYaho63slKiY-Iuf9KdkCYiBdT4dOdp4DNe8G11wItxMQMHRm5xia3ZWRBIdnlPUPUasNAQnuUVRWqRv9OZQ3AnTRuFXuHTQ8dp0CWdv-XBx1ca5T4MNJN1tegcXx9hJyYKFxJ-JqE4xdHp36spkOcMX0dJ5L_zTYTUIYnwXy0RTrTyUNnUwOOiukZEfR5U0C5_GU3qJoMoBQmYUqEpW7sERduMoFGn0pDo3gZnMljrgRt0v5an1LvYLihYcnYXhRBDhuLoJdWV0-4HMqzKquVzoL6wiX0-hTVeE4WesQ1PtOLvO4sX7Dc8h_t9sH3Bd6JJSnKAfgGduKXPjLv4k5jFW2qyjBNe5_yq7gNkMtf9w6pAqLeo91PZdPNt5_MyjrNLHLemSAGUIKvMpH_oOM6wX7zx5JD3cSA_B2nQu6pv-rG-pyhTs8pKikKhe1qtmttpg-8FN4SDnsmYaWi7tzdqaQsXzDJDLyY16mgVGqzwyKZGvugouVB8nyM5HxuN8RLeJ_DweWX9GbmVpGDwkYDLHY-qKYwsRrOQYLjPCtUP-WNmIXtvhDQlQm9_-uX33TIuUfWaoUT4PQ-ZIjQG2K7hqCjPaUJq9OzLp7e2_3Q5f9S1pq3Pn-wF4uw7IRdKEGO5mm9k4Cfh9Dt_JjSCTHvMBZNTzx-iD3Ee=w1487-h953",
		"네모", "https://lh3.googleusercontent.com/fife/AKsag4OBQp7TYXMTvwxLaODf7BhVfg3dtyuQb_2kxiqpRN8TxSu5Yh-HWbzcmA--T05fFtPj25Xl4gfnS2jzhiyTUIO7yB9yHO0sN6bj6DDZDEHngc-YGsQewFoNYidxd1TEtDOap7SfhtMAJavEpsy1e95L34ExYAUnf1RW5r69mgK90e9rh_6Iti9Xx54H5kB5yg943C0X_S6zKnypv1HG_GQD2JWv94tUuR3mhp5DNU5WLzfnA4fcv9jjQVRiHuRm5R9JxlDZYKtj_NCM5ApjUwr7x9CvQurTN30XXaidxOwWDk7Ses4DyMfcaZUHj-KtD6GbleCX556rawASShz-v7S25xRs33UBGusoiNEw33rdrZ6BhOI8t3O9hqfJATGF-MGPIOxZ_4uAV8frkaaONp54CwHMgqy9WU3zNoISW4mXMAguOaZMu70EIJMF2OTtbig8YxZ5FT-P6MqSMuFUzLlncm3m1NBhKvBv9inKzEraZINfO0KpGz8yxi8885sRkZ0whyIVK4e42FIcaHpDfX9dRigXaAmgE3jrx2CNpfuSpDPsNkxQMQBcWDhFKFUqiWqOp3lKxr1zLt8LIzrPkhRuZZywWwfe2ohuEgkXnHvTEVjqH55LXDqf7MVohzTbiDqa7AOd5RbvtkcnhHxD_3u_E6dJiupqvuzjm7QLIWgPiYNgRp45mMhm5nDZeIACihJCgElv2Uk6HENsAQqAQurFiHQyi-ahEwdR9at46RXu-OKOWyB8pWIJncuJbGE3_QdSSuYIKSWq1F8rAB_a8I5ybQ6wp7zgZNLWcw5_gWbwWui2LxBmKEFcU8UJKF7NSWbg7pBiyY_VztMnV8jPluCw82uM2_SsJnA_MwlA7R6bogEvMhEcd38q8TBNXda8zqCNRvIl2kcRxstLgliHq2hZNNt0W0CeP4wZGIwxA9xo-88M6PzJa-4I7BBSQFee6ImM-0CqSIco7_uQpfF44S6usQbAiJi0GTK5Y-L34WZdvEBbqPd3slb4VyxcraxcnEEhA62JUT26NZ6q2s5K5P2PphmZqZ0WuJ78dbxGz_fRyZNix5FnAuqYpPm6PE-wWg_ISjnglNBH8hhjyNZn31DYpsYEKZ0qKOY9oUpjDxEjrTWp_BXYL-LTBey7fAhSxzgIgyptesNU2KB_jyRAL7_0vwPgCqtnWHrgs5p9rR653YWswuqHYZBYrhrKVUQFNvZpbm8RNbS76q1H1yZ2WTLt56DMgPeP1di9hDp2Ok6GLnh_eGceQiWKaicTFPBRN_isSb1PsrLw1hM_-mNBYtruKnZURfddweROpq54z9felWF6qthMwPusuredue2MW-76RQNxOJmjSdcESBM-4haSYQ-l8IFWr6psHeKIKNRgsfjFhW7kZo0mvkpNV6MZ1kmv0x5100yH1NkaSTfVbvMDEIBAqvM__A9GGAdmMB71T6ub7JjTSrzWb3BNt2-svXlozbZn2CFyAwkdzsvCMUGaiUlCl6rT9v6QW8_Iv2BLb07NaLw5tndC5rrxZOrSsEsrePPaIRVm34-L0xe9=w1444-h963",
		"세모", "https://lh3.googleusercontent.com/fife/AKsag4Pj_f6gD7lcg8wKl77PWj2XjyR1looJofVKyH8DzHubAPM5FFhMZynY8vZdV9fbNdVsaMmYECbvODrJjohUtEHnxEc3Qjsj-dN4HjJhmA5cs21c2RteOoDCcOdrtOnq171drc5vK4TrX7c5q4js3RSw22lQgT7FCP5dMT5UWdfw7WJC60cJt0xkENnltk-9L_s_5fJSJ__v7coiR3BbJ7-773w051V8QV23vCnyVNZcVNauLykJFfLqq3pFvRRs8BJiCUcDXvxMkmNHQZ_D_ZIxAumomQ06DvL7JcxrMdigeDlXMGmyvBzSUxyzPFrMKMfpzaHgI9NdCcQyTo7qdjyFZoUJ8hEF88WhRPAO2G-qa7GUD11Ztx7Y4NzuVzG_n4OImqSfhU0qwuUiJNdOXvFJiEGt_7M7TLpElKdHN5PkK4VmXJl3yTII9QengelSPohegtTZHHAppi3HnKJZi_BRjNqas7PJy9-aZLFPmDLIDsEQ-TkKRqwtWdgfOaXf9x6wzr_v4IAPo08SUSU9N85jC1xSYd3N7JkqK-JMODTau_rpM3k0fgf6OFaTdb05Jh7mej8h8U873svE7b-KdzAkKWkugyOotu7DYOZQ3YZZ8yKupE8OrJmPWdGSy0f-PgcTgCEyiWps87PNN3Iw5RzyUPM0Zq0mSG-ZVUuWXQeQcgfMWAtw9dNtia4XLH6aTKuEeIOJPi-h_25EcoBaSCIF9Y2S_m2Qg8eBVuaO2EqgABjWfBGm_FYV6Azxh_jnxmB3OQs8RKoWQlIJNj9uqfU_xsFgiLb9JTuJccAZ5VC_iadNkO9zFD948i1DCyUuXr3-TgJxCtdtpZFsCNdhE40rRJFjmrk_LHpFWdDzF36nW8jLb2yngZFdRgllovb3CgK2K_jvmEay4qu8nUNirWKry57wdfzqC1RxEzC2psSbRHbDLxyCyIxw71TuMxaulBVyf8-Li_LLTujs3q2x6u12Byjt0WgewtPI36UqGPs5_Wi6Xe0EQfF__2NnWzfXJSGawaNhMIdw9f40oWcuqKDncLYsxMoYG10EwWYSu7lGp8hDXzkcKWqdoZb2cX2RnzCuAM7es_S4yNxU6RvpGzhYOScpmPk7MStuois-4nqsPcgSpStgkFgjcBLVobWk4LpfBKFvMEjxhWnxmAk_HTwGHyyIjMgprk-wEiLlCaQEUim4umw6ZgymfyZSFWvabwMoLXiQED8qmyWTjziqqG5MXpvjnbRUAQK1qGPSClrLuquoJTqNjqw46ToZ5ZmBooG6fbguMGGbcOHkYmOnaTkrfnDjs283a5vqiYqy90S-sWqy363RFr29Bef7reacRTpW1D5bymo5Td8OY0UObHna9gPsgVYGEFQHUSTIglDZGfKpI9j0ltAG6Nw3S3vsJTkt15QD4xWeLY5jUY_-trw60Sx9wdlZnrBaCILdOeHtsbL3IVeXDkXev1jZcQKuzkwG3ZNq6v9Ia8EeHrq5dtuVzSmOndHhlJAI6LjBKkqleA0jEeCa9fJXTqR20mGTbuA0HwW60i-FQH6yEF8b=w1444-h963",
		"마름모", "https://lh3.googleusercontent.com/fife/AKsag4PZOCNR-siFoDO-Bpcs69yCu35lpAX2HhLNmb-OdMvE5igG8WRMsZzJQ6XTiF9eSv_9jyxQvHy9k-fswQhtlCJOvXF-b05c6TlN9Wk41nfaeZ1xR2036cFnFfNbNz3hI_UcJYeGty9qLL4C5QPh6INc1PqnNwcNbPJgYEnB1l1RgRr7_Hybqa0kDMUJiJ8PON4to6KSUeI_PtAO_4FEJqGQqnjaiNiQkZFfwP2xfSiki2etwabrFN2Iv-JQvBVQ1mVed-h_302cE-3arPpbC7qSsEfq9gbjdhDOVzJH7fMshHielswu8Q-PjT_nIoly_Nc3YmHrlGae4UeBLymnQ3lc84Y_3RWLfEtI54D7L9MLUXSCm9-RyWpLBWyYnmzlbgJ87qSE-oU6SafDaD9CFV5VFo1s6awBugLRAzUPhJYzXYk_j4utE4d578m8zYUQfgfWj8YpM5Bspfqmr6QqCM8nkZe2aB8S3MUlbcDdJwlDCCJGKrBrh_yFrlAbvvCQnhhIVlXZlgUmInxYZ5SaIJv6ke5YAJN5hpwWNzX_6h0W5H40WSDS8oOXeThvuTOdOhUJsby5IggWQqtQ3vISDujs-88FN1zAZ4VLeUssq0OmIcD_8EqlhxlFDu7GSnCLBSrzL7GbZhTpqt_HL-LNfSP1Yiks_jp_wIh3UA0_PImxLZIyI3H-HRwqgGir6qfP4k8pt6tWyXLpoDcSPMLv01OnT2o5CrmlV7UvKxPiK28eKoqwNUk_KrBck_8_SFiE1X1Z1wuU5J7TLIth_JhcMX2ZrogaYwVFlyW_J5-5uaIa5fxkF_p4tif__0TnlhhFdVCJPT3SEi3BPBnx8ZtyFQor8wajj7aGQarpHV8edBWuXESRILyUGe_zIS2KsHIZoyy0sEwONBnac0ZG8sgombGw_ZEg8BTbhT9jyTnZ41MWqj7jm3ekU94M6_YUMZYUBdUuBxv_-saPWKLM4qlXoPJ-hP8YW8VkoEStwOwLuP8661uA95rOVoDYgMBLETgT_HMpw7H6N33u7ifQdnROIH7DxmKukWlkk6gxqlbMaW3kYwWarstqyvESdEprlDYNyCO1hGG2exmAxqGT6RUcsCmQgVW2yOtinH14-NFla-UKr7S6iH7J1HO6i5KK7BzGIUzbqDBMVoK6LhdKTrVZ_bWW_lFHCZcbCY5vBwVG-Ur8NjQht5br737BZfLN_9jqw2mbghyuUnTMQZedny7rI-IcIUJMzzqgXSgO64RxbSjRFQ6MFbbyISGfFN2-YfCX1Iu3cnU0TmXuGcnrAAa1aJUMh_ne03-QhEYA-JtJ0SWC0LQ7HEZBgTamH2Ld9HJw_MAK7J7LluOvWOAelIRsA_OdZ4QXSZ9NrIKJ1qDA1JJUrm24n2BaiZZZpshQNi4z8XgWP_wWSAKL7x5gBjMqVpFVqPpSaSh3SuoABmSIkekYevt-YITK2S_CbiN7G0r1p9SGJ5d33mw971LWFhlXEESjwBFc85djAb6lzhGFSJ3YyPD7Bl1wzaxsYc8Xc6KFkwPWD22PfO5LJP_dSeGK=w1011-h953",
		"별", "https://lh3.googleusercontent.com/fife/AKsag4OjrNS_kTIndvIYc9t_rv2J33WnrWep2Kf6IgVsgGjrnSdoR_lMVd3Cb0iVKnZuImPcQEVrV96_l_rkQhwnUbj2nbWGZkr5ol9pnOF0vEPbjCeIfXx6Sr8_b0EmjfTHZsERDuhETJ4ulfuLfKTwSHSdLgucXB3P5i714eg9LxAjGeCTRAS1ouODRlYWhekEjKEAvsjnxlP5nXY6twxPxzJgf89LeaJ7ivlPogqP9GB8jvBZyE2FSkACN1rABkfT67LZUFG-ub_jnXNCpgZW-PvFbQHeakDIyjOMkV7mcEaeiqwGh-vfI47nBcSx9_dTfq-NX9fbiLJVykhW0aeEj6tsywTMoZMQpL6G3a1RGXdHoVj6QPhNyBtlJkBqV7ungMF_a34ariTkKC6p-3EHxyOmm6YbZ1yelw0_W2x-9rVY0vMO4Dh_vTjEguAyZoryQeG9x_qMLts2zHalS2BZkvIzfUAbEuUxjiRvHDmXP3SroTFTNx1-a9pFKVKlcfJweNAjPr0TYCJROL0-_TRQQ76LNMpNGHv9_KkI88vSlgHCQrQXwzpwW5-MRf5TtPfj2gseUa4FiJ7h-z7gu8FNNUjDKeX6MQpSc9_2SSmwdqivQQnHR7vRvXiU2Dd4TxS5VaoBzJqB1-9jYWo991Yjs0z7yI15ZAL1LMJAtjz0eKom2YJ3Dw9WFrAhY8-Xxzt-BwJQdOqfLopqp8Dfu2So0F5h2kWjmuhaGy4aesp6JQsw7N6rBW0IX4fyJPhXa7Fe194n8qC6KZTVrj7bxP7AVYPqsPVoWc_6Yrr9TwxYlbjsqxaOhNxIRoYC1hYPbRwJ0t8VqsXfX-3ClmobP4saqlyV8AWH5FL25Ho-Rtm4o9xITBy73JeLIzUdtWI4RfOTGL6b9T5iOjWI9KQZqrFxxE0mzf4OoRryij_c3QHAoIQuiO3SLdKOKTLt0VjCNVzxN7NRPO1Q7I43ENsriiBPbvDydJsKZ3rjd6IQ67HyjcA9qscjG3mOmNaHXfnQc1Y19vCEyy2wMFuWyt9OUCQyM2zYLyRXQScY-kUjDP1J8xY2hgYnA391MtuTu7oWRV8bjRrgzFNJEeYkDzbFzJ4zX5eKJhQBN9ZtdWj8rkRRY_cgX7z9gBVunyMy3ArGs26quftkKCbZshx4Cn0DVvZOcpLrbvNyY_cDctAPOVhd5xSapr9dpI2QUJVw2FsnbcMlD_E5N6ZH1hOFZ-A8IptOw6hOT2T-x_ctUfth_RybpLeYWXNBbG1OymFOo_BwzL6JKRC1h1qmA4IYfzvS58kn3xI1E20IhZJCbf8i3Bs0YeGOjHuEzyIzU2usfqc6xtmpyoS6EK6wWkUI-HoCYBQqhRBOJmNyhe6nqxXOuNOWsFVQNWkLTIAvYh5opjSgKURKFeUSU9AR2a0xDGxsQD46cpw66BKr9M1EEcdKQNGzWsdS1ALQTkW0HwFWyYKORA60tlSBVUkQWBrfw3h3YsS-w6aUmrqhIY34Hp1_3zInx6JQxo-MuGxvmpH9zKwBbnowhZDnf4MV_NCyG3gP_a1s=w1444-h963",
		"빈칸", "https://lh3.googleusercontent.com/fife/AKsag4PI5PEeB-zSwNuxBLErNo3JEYNQxxF16QdLfbTAUH5gvmicNmwGhM1y-ynjuu3-wYWEZy7T6aIQzyZqb7eEkl_kMV37UaJOadvpqW_ss2J7jyTp07MAub2zoHUk6yp6rN1NyIwmoIbMscP6v_-OLXzkU1pMfg4BwKipLpiHZEH5bUPsF65JZVtqeHTsSXbyuMT2acqSHm0ASKH4yPVgNdMlXGRMUS7-MeTbOBHsKpqeDIOGTtSnGE9NG4cW9BQmLcpvKOaukqX6TzffAgbMDccMOZXp4GsyYtMcMQdJMHH1EbNStgN2dfNson0HWQyyc2wpqftVG9FghpJAo_QV5oXnpL3PutGrXgs7aaEw-RhgzxCSv0B927ORf-W1UfItrL3l4LZAnTEDR3Q-THqcYyF45KGkK7IgEb9lglQncOSHpeoj6ssbnwRehnkzoKNkt9njEgw4IRFQ-Gxn1_q4Qkk31ydfLtWezrYcDXS2JBk8uYoDXJMITwfTNuRxc857RvO-REjmyKrvXPoaPQgCGJOECZVkZrtuZpwlEYnIhp9HBazFH5hVoR3bvFd_v053_BvvWNK4IIKLpjCiwoTpGZUIQHrC1DuaaY17yVaW7DlTFaHH8_ov-yUNjDLum7d5ylryezi_3gxuDjha5AUxN42p2M9Mihac6JgNFDMJAbOH653dLXYIewIc5cTylpa_fcfKG0m8Xy00022XFH_AfY8kCjF3VHeUDnFaBBKyLsToh8aOZKEaf-VXs9MkaITPyYovPiPOgApa5HE2NKmtoC9gJNs-7us2dSABZQLs_YipKC2E4H3oCrEleO9zDH-S_sQYz5aDDgGUawshED82bdH4-lv1af23XzhHoUi46go_Hbs5Orsqq2ZHWwQtIbOZrLvdbR1pUdV3pZ4xENRvA6Hk4pADus4_0fRbPoqU1ptNexKNKOTzc90Du5d4I9RSrTxJEsJmkOr7q1qBSXIDMnfOU0G0I8U9P0laQUJcirAYDmmnSmYogY_kM_AXcy-YQda9VqXuwXIBlfw86MFbxf5pH67w2XIcA0Tou8qK371WnPr_Wy-5RP3zwFfpYx21FZFGQERI3e3sVRLoTUKzc5xoKfJQMuVlCURN2PHSQy-ufTUgNQN6QYFwvcRF7LMZhBdfdA9AC2ML3fnvKBxstvyIY8wlOsFcvEecreLnFAW3SH3hGgMX0a7zIrjIQ0iEwrObDxgnTFPCSbckpUqnFyQtIXw3Zmqg_0UNHlP-9puKJMIieU8FBBez0YEBVVX0ckgGtHoYbWYwbmeOYc2hNPIZqJuh14-R3DeiUog_hN004Qk7wWLT6XQ6TF1e6MnGmhdoHK_eZNTzVbbYPvNn7zqpwrYFESe_piFMrbj624xTj4Lk-jKhBvOE9x7F8Za25cnjxQ9EYPLtkBj-rhqcMzXArvZ--a0hjX1_TR9qbTWVK6c_L5KSB2YX3j5DhZyU-ai2HwbkgEbgYv4hajtstPPKd7cF39t_97c5duE9XQrR3r0zYXiHKJegfwuxPxN-HJAnA6zLTIq8Xp5__thl=w1444-h963"
	);

	static final String[] shapeName = new String[] {"동그라미", "네모" , "세모", "마름모", "별"};

	public List<DiagnosisQuizDto> getFluencyQuiz() {
		List<DiagnosisQuizDto> quizzes = new ArrayList<>(1);
		quizzes.add(generateFluencyQuiz());
		return quizzes;
	}

	public List<DiagnosisQuizDto> getVirtualQuiz() {
		List<DiagnosisQuizDto> quizzes = new ArrayList<>(1);
		quizzes.add(generateVirtualQuiz());
		return quizzes;
	}

	private DiagnosisQuizDto generateFluencyQuiz() {
		Random random = new Random();
		// Q_exec : 특정 키워드 단어 계속 말하기
		// int qIndex = random.nextInt(keywordList.length);
		int qIndex = 0;

		// keywordList[qIndex];
		return DiagnosisQuizDto.builder()
				.type(DiagnosisType.EXECUTIVE_FLUENT)
				.question("과일 이름을 생각나는대로 최대한 많이 말해주세요.")
				.shortAnswer(Collections.singletonList(keywordList[qIndex]))
				.build();
	}

	private DiagnosisQuizDto generateVirtualQuiz() {
		Random random = new Random();
		List<String> shapes = new ArrayList<>(Arrays.asList(shapeName));
		Collections.shuffle(shapes);
		System.out.println(shapes);
		shapes = shapes.subList(0, 4);
		System.out.println(shapes);
		List<String> pattern = new ArrayList<>(shapes.subList(0, 3));
		int answer = random.nextInt(3);

		String answerShape = "";
		for (int i = 0; i < 3; i++) {
			if (i == answer) {
				pattern.add("빈칸");
				answerShape = pattern.get(i);
			} else {
				pattern.add(pattern.get(i));
			}
		}

		StringBuilder question = new StringBuilder();
		question.append("다음 빈칸에 들어올 그림은 무엇 일까요? <br><br><div>");
		for (int i = 0; i < 6; ++i) {
			question.append("<img src=\"")
					.append(map.get(pattern.get(i)))
					.append("\" alt=\"quiz_img\" style = \"width:100px; height:100px; margin-left:30px;\">");
		}
		question.append("</div>");

		System.out.println(shapes);
		List<String> choice = shapes.stream().map(imgSrc ->
				"<img src=\"" + map.get(imgSrc)
				+ "\" alt=\"quiz_img\" style = \"width:100px; height:100px; margin-left:30px;\">").toList();
		System.out.println(shapes);
		System.out.println(choice);
		return DiagnosisQuizDto.builder()
			.type(DiagnosisType.EXECUTIVE_VIRTUAL)
			.question(question.toString())
			.choice(choice)
			.choiceAnswer(shapes.indexOf(answerShape))
			.build();
	}
}