package ru.mentorbank.backoffice.services.stoplist;

import ru.mentorbank.backoffice.model.stoplist.JuridicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.PhysicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.StopListInfo;
import ru.mentorbank.backoffice.model.stoplist.StopListStatus;
import ru.mentorbank.backoffice.services.stoplist.StopListService;

public class StopListServiceStub implements StopListService {

	public static final String INN_FOR_OK_STATUS = "1111111111111";
	public static final String INN_FOR_STOP_STATUS = "22222222222222";
	public static final String INN_FOR_ASKSECURITY_STATUS = "33333333333333";
        
        public static final String DOCUMENT_NUMBER_OK = "11111";
	public static final String DOCUMENT_SERIAL_OK = "AAAAAA";
	public static final String LASTNAME_OK = "POLATOV";
	public static final String FIRSTNAME_OK = "ALIBEK";
	public static final String MIDDLENAME_OK = "NIGMATOVICH";

	public static final String DOCUMENT_NUMBER_STOP = "22222";
	public static final String DOCUMENT_SERIAL_STOP = "BBBBB";
	public static final String LASTNAME_STOP = "POLATOVA";
	public static final String FIRSTNAME_STOP = "DIANA";
	public static final String MIDDLENAME_STOP = "NIGMATOVNA";

        
	@Override
	public StopListInfo getJuridicalStopListInfo(
			JuridicalStopListRequest request) {
		StopListInfo stopListInfo = new StopListInfo();
		stopListInfo.setComment("�����������");
		if (INN_FOR_OK_STATUS.equals(request.getInn())){			
			stopListInfo.setStatus(StopListStatus.OK);
		} else if (INN_FOR_STOP_STATUS.equals(request.getInn())) {
			stopListInfo.setStatus(StopListStatus.STOP);			
		} else {
			stopListInfo.setStatus(StopListStatus.ASKSECURITY);			
		}
		return stopListInfo;
	}

	@Override
	public StopListInfo getPhysicalStopListInfo(PhysicalStopListRequest request) {

         	StopListInfo stopListInfo = new StopListInfo();
		stopListInfo.setComment("Kommentari");
		if (DOCUMENT_NUMBER_OK.equals(request.getDocumentNumber())
				&& DOCUMENT_SERIAL_OK.equals(request.getDocumentSeries())
				&& LASTNAME_OK.equals(request.getLastname())
				&& FIRSTNAME_OK.equals(request.getFirstname())
				&& MIDDLENAME_OK.equals(request.getMiddlename())) {
			stopListInfo.setStatus(StopListStatus.OK);
		} else if (DOCUMENT_NUMBER_STOP.equals(request.getDocumentNumber())
				&& DOCUMENT_SERIAL_STOP.equals(request.getDocumentSeries())
				&& LASTNAME_STOP.equals(request.getLastname())
				&& FIRSTNAME_STOP.equals(request.getFirstname())
				&& MIDDLENAME_STOP.equals(request.getMiddlename())) {
			stopListInfo.setStatus(StopListStatus.STOP);
		} else {
			stopListInfo.setStatus(StopListStatus.ASKSECURITY);
		}

		return stopListInfo;
	}



}
