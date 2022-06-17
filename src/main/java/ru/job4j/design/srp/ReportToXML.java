package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class ReportToXML implements Report {
    private Store store;

    public ReportToXML(Store store) {
        this.store = store;
    }


    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new Employees(store.findBy(filter)), writer);
                xml = writer.getBuffer().toString();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return xml;
    }

    @XmlRootElement(name = "employees")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Employees {
        @XmlElement(name = "employee")
        private List<Employee> list;

        public Employees() {
        }

        public Employees(List<Employee> list) {
            this.list = list;
        }

        public List<Employee> getList() {
            return list;
        }

        public void setList(List<Employee> list) {
            this.list = list;
        }
    }
}
