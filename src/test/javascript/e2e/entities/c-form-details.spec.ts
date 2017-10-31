import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('CFormDetails e2e test', () => {

    let navBarPage: NavBarPage;
    let cFormDetailsDialogPage: CFormDetailsDialogPage;
    let cFormDetailsComponentsPage: CFormDetailsComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load CFormDetails', () => {
        navBarPage.goToEntity('c-form-details');
        cFormDetailsComponentsPage = new CFormDetailsComponentsPage();
        expect(cFormDetailsComponentsPage.getTitle()).toMatch(/samplePmsApp.cFormDetails.home.title/);

    });

    it('should load create CFormDetails dialog', () => {
        cFormDetailsComponentsPage.clickOnCreateButton();
        cFormDetailsDialogPage = new CFormDetailsDialogPage();
        expect(cFormDetailsDialogPage.getModalTitle()).toMatch(/samplePmsApp.cFormDetails.home.createOrEditLabel/);
        cFormDetailsDialogPage.close();
    });

    it('should create and save CFormDetails', () => {
        cFormDetailsComponentsPage.clickOnCreateButton();
        cFormDetailsDialogPage.setDateOfBirthInput('2000-12-31');
        expect(cFormDetailsDialogPage.getDateOfBirthInput()).toMatch('2000-12-31');
        cFormDetailsDialogPage.setPlaceOfBirthInput('placeOfBirth');
        expect(cFormDetailsDialogPage.getPlaceOfBirthInput()).toMatch('placeOfBirth');
        cFormDetailsDialogPage.setArrivedFromInput('arrivedFrom');
        expect(cFormDetailsDialogPage.getArrivedFromInput()).toMatch('arrivedFrom');
        cFormDetailsDialogPage.setDepartingToInput('departingTo');
        expect(cFormDetailsDialogPage.getDepartingToInput()).toMatch('departingTo');
        cFormDetailsDialogPage.setPassportNoInput('passportNo');
        expect(cFormDetailsDialogPage.getPassportNoInput()).toMatch('passportNo');
        cFormDetailsDialogPage.setPassportExpirationDateInput('2000-12-31');
        expect(cFormDetailsDialogPage.getPassportExpirationDateInput()).toMatch('2000-12-31');
        cFormDetailsDialogPage.getEmployedinIndiaInput().isSelected().then(function (selected) {
            if (selected) {
                cFormDetailsDialogPage.getEmployedinIndiaInput().click();
                expect(cFormDetailsDialogPage.getEmployedinIndiaInput().isSelected()).toBeFalsy();
            } else {
                cFormDetailsDialogPage.getEmployedinIndiaInput().click();
                expect(cFormDetailsDialogPage.getEmployedinIndiaInput().isSelected()).toBeTruthy();
            }
        });
        cFormDetailsDialogPage.setPassportIssuedAuthorityInput('passportIssuedAuthority');
        expect(cFormDetailsDialogPage.getPassportIssuedAuthorityInput()).toMatch('passportIssuedAuthority');
        cFormDetailsDialogPage.setDateOfArrivalInIndiaInput('2000-12-31');
        expect(cFormDetailsDialogPage.getDateOfArrivalInIndiaInput()).toMatch('2000-12-31');
        cFormDetailsDialogPage.setPlaceOfIssueInput('placeOfIssue');
        expect(cFormDetailsDialogPage.getPlaceOfIssueInput()).toMatch('placeOfIssue');
        cFormDetailsDialogPage.setVisaTypeInput('visaType');
        expect(cFormDetailsDialogPage.getVisaTypeInput()).toMatch('visaType');
        cFormDetailsDialogPage.setVisaNoInput('visaNo');
        expect(cFormDetailsDialogPage.getVisaNoInput()).toMatch('visaNo');
        cFormDetailsDialogPage.setVisaEffectiveDateInput('2000-12-31');
        expect(cFormDetailsDialogPage.getVisaEffectiveDateInput()).toMatch('2000-12-31');
        cFormDetailsDialogPage.setVisaExpiryDateInput('2000-12-31');
        expect(cFormDetailsDialogPage.getVisaExpiryDateInput()).toMatch('2000-12-31');
        cFormDetailsDialogPage.setVisaIssuedAuthorityNameInput('visaIssuedAuthorityName');
        expect(cFormDetailsDialogPage.getVisaIssuedAuthorityNameInput()).toMatch('visaIssuedAuthorityName');
        cFormDetailsDialogPage.save();
        expect(cFormDetailsDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class CFormDetailsComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-c-form-details div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class CFormDetailsDialogPage {
    modalTitle = element(by.css('h4#myCFormDetailsLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    dateOfBirthInput = element(by.css('input#field_dateOfBirth'));
    placeOfBirthInput = element(by.css('input#field_placeOfBirth'));
    arrivedFromInput = element(by.css('input#field_arrivedFrom'));
    departingToInput = element(by.css('input#field_departingTo'));
    passportNoInput = element(by.css('input#field_passportNo'));
    passportExpirationDateInput = element(by.css('input#field_passportExpirationDate'));
    employedinIndiaInput = element(by.css('input#field_employedinIndia'));
    passportIssuedAuthorityInput = element(by.css('input#field_passportIssuedAuthority'));
    dateOfArrivalInIndiaInput = element(by.css('input#field_dateOfArrivalInIndia'));
    placeOfIssueInput = element(by.css('input#field_placeOfIssue'));
    visaTypeInput = element(by.css('input#field_visaType'));
    visaNoInput = element(by.css('input#field_visaNo'));
    visaEffectiveDateInput = element(by.css('input#field_visaEffectiveDate'));
    visaExpiryDateInput = element(by.css('input#field_visaExpiryDate'));
    visaIssuedAuthorityNameInput = element(by.css('input#field_visaIssuedAuthorityName'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setDateOfBirthInput = function (dateOfBirth) {
        this.dateOfBirthInput.sendKeys(dateOfBirth);
    }

    getDateOfBirthInput = function () {
        return this.dateOfBirthInput.getAttribute('value');
    }

    setPlaceOfBirthInput = function (placeOfBirth) {
        this.placeOfBirthInput.sendKeys(placeOfBirth);
    }

    getPlaceOfBirthInput = function () {
        return this.placeOfBirthInput.getAttribute('value');
    }

    setArrivedFromInput = function (arrivedFrom) {
        this.arrivedFromInput.sendKeys(arrivedFrom);
    }

    getArrivedFromInput = function () {
        return this.arrivedFromInput.getAttribute('value');
    }

    setDepartingToInput = function (departingTo) {
        this.departingToInput.sendKeys(departingTo);
    }

    getDepartingToInput = function () {
        return this.departingToInput.getAttribute('value');
    }

    setPassportNoInput = function (passportNo) {
        this.passportNoInput.sendKeys(passportNo);
    }

    getPassportNoInput = function () {
        return this.passportNoInput.getAttribute('value');
    }

    setPassportExpirationDateInput = function (passportExpirationDate) {
        this.passportExpirationDateInput.sendKeys(passportExpirationDate);
    }

    getPassportExpirationDateInput = function () {
        return this.passportExpirationDateInput.getAttribute('value');
    }

    getEmployedinIndiaInput = function () {
        return this.employedinIndiaInput;
    }
    setPassportIssuedAuthorityInput = function (passportIssuedAuthority) {
        this.passportIssuedAuthorityInput.sendKeys(passportIssuedAuthority);
    }

    getPassportIssuedAuthorityInput = function () {
        return this.passportIssuedAuthorityInput.getAttribute('value');
    }

    setDateOfArrivalInIndiaInput = function (dateOfArrivalInIndia) {
        this.dateOfArrivalInIndiaInput.sendKeys(dateOfArrivalInIndia);
    }

    getDateOfArrivalInIndiaInput = function () {
        return this.dateOfArrivalInIndiaInput.getAttribute('value');
    }

    setPlaceOfIssueInput = function (placeOfIssue) {
        this.placeOfIssueInput.sendKeys(placeOfIssue);
    }

    getPlaceOfIssueInput = function () {
        return this.placeOfIssueInput.getAttribute('value');
    }

    setVisaTypeInput = function (visaType) {
        this.visaTypeInput.sendKeys(visaType);
    }

    getVisaTypeInput = function () {
        return this.visaTypeInput.getAttribute('value');
    }

    setVisaNoInput = function (visaNo) {
        this.visaNoInput.sendKeys(visaNo);
    }

    getVisaNoInput = function () {
        return this.visaNoInput.getAttribute('value');
    }

    setVisaEffectiveDateInput = function (visaEffectiveDate) {
        this.visaEffectiveDateInput.sendKeys(visaEffectiveDate);
    }

    getVisaEffectiveDateInput = function () {
        return this.visaEffectiveDateInput.getAttribute('value');
    }

    setVisaExpiryDateInput = function (visaExpiryDate) {
        this.visaExpiryDateInput.sendKeys(visaExpiryDate);
    }

    getVisaExpiryDateInput = function () {
        return this.visaExpiryDateInput.getAttribute('value');
    }

    setVisaIssuedAuthorityNameInput = function (visaIssuedAuthorityName) {
        this.visaIssuedAuthorityNameInput.sendKeys(visaIssuedAuthorityName);
    }

    getVisaIssuedAuthorityNameInput = function () {
        return this.visaIssuedAuthorityNameInput.getAttribute('value');
    }

    save() {
        this.saveButton.click();
    }

    close() {
        this.closeButton.click();
    }

    getSaveButton() {
        return this.saveButton;
    }
}
