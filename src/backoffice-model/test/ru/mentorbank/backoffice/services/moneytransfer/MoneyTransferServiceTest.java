package ru.mentorbank.backoffice.services.moneytransfer;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mentorbank.backoffice.dao.OperationDao;
import ru.mentorbank.backoffice.dao.exception.OperationDaoException;
import ru.mentorbank.backoffice.dao.stub.OperationDaoStub;
import ru.mentorbank.backoffice.model.Operation;
import ru.mentorbank.backoffice.model.stoplist.JuridicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.PhysicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.StopListInfo;
import ru.mentorbank.backoffice.model.stoplist.StopListStatus;
import ru.mentorbank.backoffice.model.transfer.AccountInfo;
import ru.mentorbank.backoffice.model.transfer.JuridicalAccountInfo;
import ru.mentorbank.backoffice.model.transfer.PhysicalAccountInfo;
import ru.mentorbank.backoffice.model.transfer.TransferRequest;
import ru.mentorbank.backoffice.services.accounts.AccountService;
import ru.mentorbank.backoffice.services.accounts.AccountServiceBean;
import ru.mentorbank.backoffice.services.moneytransfer.exceptions.TransferException;
import ru.mentorbank.backoffice.services.stoplist.StopListService;
import ru.mentorbank.backoffice.services.stoplist.StopListServiceStub;
import ru.mentorbank.backoffice.test.AbstractSpringTest;
import static org.mockito.Mockito.*;

public class MoneyTransferServiceTest extends AbstractSpringTest {

	@Autowired
	private MoneyTransferServiceBean moneyTransferService;
        private StopListService mockStopListService;
	private AccountService mockAccountService;
	private OperationDao mockOperationDao;
	private JuridicalAccountInfo srcAccInfo;
	private PhysicalAccountInfo dstAccInfo;
	private TransferRequest transferRequest;
	private StopListInfo SLI;

	@Before
	public void setUp() {
                mockStopListService = mock(StopListServiceStub.class);
		mockAccountService = mock(AccountServiceBean.class);
		mockOperationDao = mock(OperationDaoStub.class);

		srcAccInfo = new JuridicalAccountInfo();
		dstAccInfo = new PhysicalAccountInfo();
		transferRequest = new TransferRequest();
		moneyTransferService = new MoneyTransferServiceBean();
		SLI = new StopListInfo();
		srcAccInfo.setInn(StopListServiceStub.INN_FOR_OK_STATUS);
		dstAccInfo.setAccountNumber("1111111111111");
		SLI.setStatus(StopListStatus.OK);
		moneyTransferService.setAccountService(mockAccountService);
		moneyTransferService.setStopListService(mockStopListService);
		moneyTransferService.setOperationDao(mockOperationDao);
		transferRequest.setSrcAccount(srcAccInfo);
		transferRequest.setDstAccount(dstAccInfo);

	}

	@Test
	public void transfer() throws TransferException,OperationDaoException {
                
                when(mockAccountService.verifyBalance(any(AccountInfo.class)))
				.thenReturn(true);

		when(
				mockStopListService
						.getJuridicalStopListInfo(any(JuridicalStopListRequest.class)))
				.thenReturn(SLI);
		when(
				mockStopListService
						.getPhysicalStopListInfo(any(PhysicalStopListRequest.class)))
				.thenReturn(SLI);
		moneyTransferService.transfer(transferRequest);
		verify(mockStopListService).getJuridicalStopListInfo(
				any(JuridicalStopListRequest.class));
		verify(mockAccountService).verifyBalance(srcAccInfo);
		verify(mockOperationDao).saveOperation(any(Operation.class));

	}

}
