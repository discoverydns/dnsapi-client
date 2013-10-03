package com.discoverydns.dnsapiclient.internal;

import org.xbill.DNS.AAAARecord;
import org.xbill.DNS.ARecord;
import org.xbill.DNS.CERTRecord;
import org.xbill.DNS.CNAMERecord;
import org.xbill.DNS.DSRecord;
import org.xbill.DNS.LOCRecord;
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.NAPTRRecord;
import org.xbill.DNS.NSRecord;
import org.xbill.DNS.NULLRecord;
import org.xbill.DNS.PTRRecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.SOARecord;
import org.xbill.DNS.SPFRecord;
import org.xbill.DNS.SRVRecord;
import org.xbill.DNS.SSHFPRecord;
import org.xbill.DNS.TLSARecord;
import org.xbill.DNS.TXTRecord;
import org.xbill.DNS.Type;

import com.discoverydns.dnsapiclient.ObjectMapperFactory;
import com.discoverydns.dnsapiclient.command.nameServerInterfaceSet.NameServerInterface;
import com.discoverydns.dnsapiclient.internal.json.nameserverinterfaceset.NameServerInterfaceDeserializer;
import com.discoverydns.dnsapiclient.internal.json.nameserverinterfaceset.NameServerInterfaceSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.AAAARecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.AAAARecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.ARecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.ARecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.CERTRecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.CERTRecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.CNAMERecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.CNAMERecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.DSRecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.DSRecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.LOCRecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.LOCRecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.MXRecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.MXRecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.NAPTRRecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.NAPTRRecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.NSRecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.NSRecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.NULLRecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.PTRRecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.PTRRecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.RecordTypeReferenceDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.SOARecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.SOARecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.SPFRecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.SPFRecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.SRVRecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.SRVRecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.SSHFPRecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.SSHFPRecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.TLSARecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.TLSARecordSerializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.TXTRecordDeserializer;
import com.discoverydns.dnsapiclient.internal.json.resourcerecords.TXTRecordSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class DefaultObjectMapperFactory implements ObjectMapperFactory {

	@Override
	public ObjectMapper createInstance() {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JodaModule());

		mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, false);
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, false);
		mapper.configure(
				JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER,
				false);
		mapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, false);
		mapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, false);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, false);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, false);
		mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
		mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
		mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT, true);
		mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, true);
		mapper.configure(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM, true);
		mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);
		mapper.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
		mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, false);

		mapper.configure(MapperFeature.USE_ANNOTATIONS, true);
		mapper.configure(MapperFeature.AUTO_DETECT_CREATORS, true);
		mapper.configure(MapperFeature.AUTO_DETECT_FIELDS, true);
		mapper.configure(MapperFeature.AUTO_DETECT_GETTERS, true);
		mapper.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, true);
		mapper.configure(MapperFeature.AUTO_DETECT_SETTERS, true);
		mapper.configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, false);
		mapper.configure(MapperFeature.USE_GETTERS_AS_SETTERS, true);
		mapper.configure(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS, true);
		mapper.configure(MapperFeature.INFER_PROPERTY_MUTATORS, false);
		mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, false);
		mapper.configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, false);
		mapper.configure(MapperFeature.USE_STATIC_TYPING, false);
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
		mapper.configure(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS, false);

		mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS,
				false);
		mapper.configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, false);
		mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY,
				false);
		mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING,
				false);
		mapper.configure(
				DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
				false);
		mapper.configure(
				DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, false);
		mapper.configure(
				DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS,
				false);
		mapper.configure(
				DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES,
				true);
		mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
		mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, true);
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
				false);
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		mapper.configure(DeserializationFeature.EAGER_DESERIALIZER_FETCH, true);
		mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS, true);

		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
		mapper.configure(SerializationFeature.WRAP_EXCEPTIONS, true);
		mapper.configure(SerializationFeature.CLOSE_CLOSEABLE, false);
		mapper.configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, true);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,
				false);
		mapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS,
				false);
		mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING,
				false);
		mapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, false);
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
		mapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true);
		mapper.configure(
				SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, false);
		mapper.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN, false);
		mapper.configure(
				SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS,
				false);
		mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, false);
		mapper.configure(SerializationFeature.EAGER_SERIALIZER_FETCH, true);

		mapper.registerModule(zoneResourceRecordModule());
		mapper.registerModule(nameServerInterfaceModule());
		return mapper;
	}

	private SimpleModule zoneResourceRecordModule() {
		final SimpleModule module = new SimpleModule("ZoneResourceRecordModule");

		module.addDeserializer(Record.class, recordTypeReferenceDeserializer());

		// Register managed record types' deserializers here
		module.addDeserializer(ARecord.class, aRecordDeserializer());
		module.addDeserializer(NSRecord.class, nsRecordDeserializer());
		module.addDeserializer(SOARecord.class, soaRecordDeserializer());
		module.addDeserializer(AAAARecord.class, aaaaRecordDeserializer());
		module.addDeserializer(MXRecord.class, mxRecordDeserializer());
		module.addDeserializer(CNAMERecord.class, cnameRecordDeserializer());
		module.addDeserializer(TXTRecord.class, txtRecordDeserializer());
		module.addDeserializer(SRVRecord.class, srvRecordDeserializer());
		module.addDeserializer(NAPTRRecord.class, naptrRecordDeserializer());
		module.addDeserializer(SPFRecord.class, spfRecordDeserializer());
		module.addDeserializer(DSRecord.class, dsRecordDeserializer());
		module.addDeserializer(CERTRecord.class, certRecordDeserializer());
		module.addDeserializer(PTRRecord.class, ptrRecordDeserializer());
		module.addDeserializer(SSHFPRecord.class, sshfpRecordDeserializer());
		module.addDeserializer(TLSARecord.class, tlsaRecordDeserializer());
		module.addDeserializer(LOCRecord.class, locRecordDeserializer());

		// Register managed record types' serializers here
		module.addSerializer(ARecord.class, aRecordSerializer());
		module.addSerializer(NSRecord.class, nsRecordSerializer());
		module.addSerializer(SOARecord.class, soaRecordSerializer());
		module.addSerializer(AAAARecord.class, aaaaRecordSerializer());
		module.addSerializer(MXRecord.class, mxRecordSerializer());
		module.addSerializer(CNAMERecord.class, cnameRecordSerializer());
		module.addSerializer(TXTRecord.class, txtRecordSerializer());
		module.addSerializer(SRVRecord.class, srvRecordSerializer());
		module.addSerializer(NAPTRRecord.class, naptrRecordSerializer());
		module.addSerializer(SPFRecord.class, spfRecordSerializer());
		module.addSerializer(DSRecord.class, dsRecordSerializer());
		module.addSerializer(CERTRecord.class, certRecordSerializer());
		module.addSerializer(PTRRecord.class, ptrRecordSerializer());
		module.addSerializer(NULLRecord.class, nullRecordSerializer());
		module.addSerializer(SSHFPRecord.class, sshfpRecordSerializer());
		module.addSerializer(TLSARecord.class, tlsaRecordSerializer());
		module.addSerializer(LOCRecord.class, locRecordSerializer());

		return module;
	}

	private RecordTypeReferenceDeserializer recordTypeReferenceDeserializer() {
		final RecordTypeReferenceDeserializer recordTypeReferenceDeserializer = new RecordTypeReferenceDeserializer();

		// Register managed record types here
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.A), ARecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.NS), NSRecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.SOA), SOARecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.AAAA), AAAARecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.MX), MXRecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.CNAME), CNAMERecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.TXT), TXTRecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.SRV), SRVRecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.NAPTR), NAPTRRecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.SPF), SPFRecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.DS), DSRecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.CERT), CERTRecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.PTR), PTRRecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.SSHFP), SSHFPRecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.TLSA), TLSARecord.class);
		recordTypeReferenceDeserializer.registerRecordType(
				String.valueOf(Type.LOC), LOCRecord.class);

		return recordTypeReferenceDeserializer;
	}

	private ARecordDeserializer aRecordDeserializer() {
		return new ARecordDeserializer();
	}

	private ARecordSerializer aRecordSerializer() {
		return new ARecordSerializer();
	}

	private NSRecordDeserializer nsRecordDeserializer() {
		return new NSRecordDeserializer();
	}

	private NSRecordSerializer nsRecordSerializer() {
		return new NSRecordSerializer();
	}

	private SOARecordDeserializer soaRecordDeserializer() {
		return new SOARecordDeserializer();
	}

	private SOARecordSerializer soaRecordSerializer() {
		return new SOARecordSerializer();
	}

	private AAAARecordDeserializer aaaaRecordDeserializer() {
		return new AAAARecordDeserializer();
	}

	private AAAARecordSerializer aaaaRecordSerializer() {
		return new AAAARecordSerializer();
	}

	private MXRecordDeserializer mxRecordDeserializer() {
		return new MXRecordDeserializer();
	}

	private MXRecordSerializer mxRecordSerializer() {
		return new MXRecordSerializer();
	}

	private CNAMERecordDeserializer cnameRecordDeserializer() {
		return new CNAMERecordDeserializer();
	}

	private CNAMERecordSerializer cnameRecordSerializer() {
		return new CNAMERecordSerializer();
	}

	private TXTRecordDeserializer txtRecordDeserializer() {
		return new TXTRecordDeserializer();
	}

	private TXTRecordSerializer txtRecordSerializer() {
		return new TXTRecordSerializer();
	}

	private SRVRecordDeserializer srvRecordDeserializer() {
		return new SRVRecordDeserializer();
	}

	private SRVRecordSerializer srvRecordSerializer() {
		return new SRVRecordSerializer();
	}

	private NAPTRRecordDeserializer naptrRecordDeserializer() {
		return new NAPTRRecordDeserializer();
	}

	private NAPTRRecordSerializer naptrRecordSerializer() {
		return new NAPTRRecordSerializer();
	}

	private SPFRecordDeserializer spfRecordDeserializer() {
		return new SPFRecordDeserializer();
	}

	private SPFRecordSerializer spfRecordSerializer() {
		return new SPFRecordSerializer();
	}

	private DSRecordDeserializer dsRecordDeserializer() {
		return new DSRecordDeserializer();
	}

	private DSRecordSerializer dsRecordSerializer() {
		return new DSRecordSerializer();
	}

	private CERTRecordDeserializer certRecordDeserializer() {
		return new CERTRecordDeserializer();
	}

	private CERTRecordSerializer certRecordSerializer() {
		return new CERTRecordSerializer();
	}

	private PTRRecordDeserializer ptrRecordDeserializer() {
		return new PTRRecordDeserializer();
	}

	private PTRRecordSerializer ptrRecordSerializer() {
		return new PTRRecordSerializer();
	}

	private NULLRecordSerializer nullRecordSerializer() {
		return new NULLRecordSerializer();
	}

	private SSHFPRecordDeserializer sshfpRecordDeserializer() {
		return new SSHFPRecordDeserializer();
	}

	private SSHFPRecordSerializer sshfpRecordSerializer() {
		return new SSHFPRecordSerializer();
	}

	private TLSARecordDeserializer tlsaRecordDeserializer() {
		return new TLSARecordDeserializer();
	}

	private TLSARecordSerializer tlsaRecordSerializer() {
		return new TLSARecordSerializer();
	}

	private LOCRecordDeserializer locRecordDeserializer() {
		return new LOCRecordDeserializer();
	}

	private LOCRecordSerializer locRecordSerializer() {
		return new LOCRecordSerializer();
	}

	public SimpleModule nameServerInterfaceModule() {
		SimpleModule module = new SimpleModule("NameServerInterfaceModule");

		module.addDeserializer(NameServerInterface.class,
				nameServerInterfaceDeserializer());

		module.addSerializer(NameServerInterface.class,
				nameServerInterfaceSerializer());

		return module;
	}

	public NameServerInterfaceDeserializer nameServerInterfaceDeserializer() {
		return new NameServerInterfaceDeserializer();
	}

	public NameServerInterfaceSerializer nameServerInterfaceSerializer() {
		return new NameServerInterfaceSerializer();
	}

}
