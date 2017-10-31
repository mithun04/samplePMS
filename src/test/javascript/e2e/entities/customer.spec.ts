import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Customer e2e test', () => {

    let navBarPage: NavBarPage;
    let customerDialogPage: CustomerDialogPage;
    let customerComponentsPage: CustomerComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Customers', () => {
        navBarPage.goToEntity('customer');
        customerComponentsPage = new CustomerComponentsPage();
        expect(customerComponentsPage.getTitle()).toMatch(/samplePmsApp.customer.home.title/);

    });

    it('should load create Customer dialog', () => {
        customerComponentsPage.clickOnCreateButton();
        customerDialogPage = new CustomerDialogPage();
        expect(customerDialogPage.getModalTitle()).toMatch(/samplePmsApp.customer.home.createOrEditLabel/);
        customerDialogPage.close();
    });

    it('should create and save Customers', () => {
        customerComponentsPage.clickOnCreateButton();
        customerDialogPage.setCrmContactIdInput('crmContactId');
        expect(customerDialogPage.getCrmContactIdInput()).toMatch('crmContactId');
        customerDialogPage.setLeadIdInput('leadId');
        expect(customerDialogPage.getLeadIdInput()).toMatch('leadId');
        customerDialogPage.setGstNumberInput('gstNumber');
        expect(customerDialogPage.getGstNumberInput()).toMatch('gstNumber');
        customerDialogPage.setTitleInput('title');
        expect(customerDialogPage.getTitleInput()).toMatch('title');
        customerDialogPage.setFirst_nameInput('first_name');
        expect(customerDialogPage.getFirst_nameInput()).toMatch('first_name');
        customerDialogPage.setLast_nameInput('last_name');
        expect(customerDialogPage.getLast_nameInput()).toMatch('last_name');
        customerDialogPage.setGenderInput('gender');
        expect(customerDialogPage.getGenderInput()).toMatch('gender');
        customerDialogPage.setTelephone_noInput('telephone_no');
        expect(customerDialogPage.getTelephone_noInput()).toMatch('telephone_no');
        customerDialogPage.setMobile_noInput('mobile_no');
        expect(customerDialogPage.getMobile_noInput()).toMatch('mobile_no');
        customerDialogPage.setEmail_idInput('email_id');
        expect(customerDialogPage.getEmail_idInput()).toMatch('email_id');
        customerDialogPage.setAddressInput('address');
        expect(customerDialogPage.getAddressInput()).toMatch('address');
        customerDialogPage.setCityInput('city');
        expect(customerDialogPage.getCityInput()).toMatch('city');
        customerDialogPage.setProvinceInput('province');
        expect(customerDialogPage.getProvinceInput()).toMatch('province');
        customerDialogPage.setCountryInput('country');
        expect(customerDialogPage.getCountryInput()).toMatch('country');
        customerDialogPage.setPostal_codeInput('postal_code');
        expect(customerDialogPage.getPostal_codeInput()).toMatch('postal_code');
        customerDialogPage.save();
        expect(customerDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class CustomerComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-customer div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class CustomerDialogPage {
    modalTitle = element(by.css('h4#myCustomerLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    crmContactIdInput = element(by.css('input#field_crmContactId'));
    leadIdInput = element(by.css('input#field_leadId'));
    gstNumberInput = element(by.css('input#field_gstNumber'));
    titleInput = element(by.css('input#field_title'));
    first_nameInput = element(by.css('input#field_first_name'));
    last_nameInput = element(by.css('input#field_last_name'));
    genderInput = element(by.css('input#field_gender'));
    telephone_noInput = element(by.css('input#field_telephone_no'));
    mobile_noInput = element(by.css('input#field_mobile_no'));
    email_idInput = element(by.css('input#field_email_id'));
    addressInput = element(by.css('input#field_address'));
    cityInput = element(by.css('input#field_city'));
    provinceInput = element(by.css('input#field_province'));
    countryInput = element(by.css('input#field_country'));
    postal_codeInput = element(by.css('input#field_postal_code'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setCrmContactIdInput = function (crmContactId) {
        this.crmContactIdInput.sendKeys(crmContactId);
    }

    getCrmContactIdInput = function () {
        return this.crmContactIdInput.getAttribute('value');
    }

    setLeadIdInput = function (leadId) {
        this.leadIdInput.sendKeys(leadId);
    }

    getLeadIdInput = function () {
        return this.leadIdInput.getAttribute('value');
    }

    setGstNumberInput = function (gstNumber) {
        this.gstNumberInput.sendKeys(gstNumber);
    }

    getGstNumberInput = function () {
        return this.gstNumberInput.getAttribute('value');
    }

    setTitleInput = function (title) {
        this.titleInput.sendKeys(title);
    }

    getTitleInput = function () {
        return this.titleInput.getAttribute('value');
    }

    setFirst_nameInput = function (first_name) {
        this.first_nameInput.sendKeys(first_name);
    }

    getFirst_nameInput = function () {
        return this.first_nameInput.getAttribute('value');
    }

    setLast_nameInput = function (last_name) {
        this.last_nameInput.sendKeys(last_name);
    }

    getLast_nameInput = function () {
        return this.last_nameInput.getAttribute('value');
    }

    setGenderInput = function (gender) {
        this.genderInput.sendKeys(gender);
    }

    getGenderInput = function () {
        return this.genderInput.getAttribute('value');
    }

    setTelephone_noInput = function (telephone_no) {
        this.telephone_noInput.sendKeys(telephone_no);
    }

    getTelephone_noInput = function () {
        return this.telephone_noInput.getAttribute('value');
    }

    setMobile_noInput = function (mobile_no) {
        this.mobile_noInput.sendKeys(mobile_no);
    }

    getMobile_noInput = function () {
        return this.mobile_noInput.getAttribute('value');
    }

    setEmail_idInput = function (email_id) {
        this.email_idInput.sendKeys(email_id);
    }

    getEmail_idInput = function () {
        return this.email_idInput.getAttribute('value');
    }

    setAddressInput = function (address) {
        this.addressInput.sendKeys(address);
    }

    getAddressInput = function () {
        return this.addressInput.getAttribute('value');
    }

    setCityInput = function (city) {
        this.cityInput.sendKeys(city);
    }

    getCityInput = function () {
        return this.cityInput.getAttribute('value');
    }

    setProvinceInput = function (province) {
        this.provinceInput.sendKeys(province);
    }

    getProvinceInput = function () {
        return this.provinceInput.getAttribute('value');
    }

    setCountryInput = function (country) {
        this.countryInput.sendKeys(country);
    }

    getCountryInput = function () {
        return this.countryInput.getAttribute('value');
    }

    setPostal_codeInput = function (postal_code) {
        this.postal_codeInput.sendKeys(postal_code);
    }

    getPostal_codeInput = function () {
        return this.postal_codeInput.getAttribute('value');
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
